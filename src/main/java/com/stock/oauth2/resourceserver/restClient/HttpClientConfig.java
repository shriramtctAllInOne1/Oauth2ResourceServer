/**
 * The ResourceServer Project, BSD License,Copyright (c) 2019
 * All rights reserved.
**/
package com.stock.oauth2.resourceserver.restClient;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContextBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.stock.oauth2.resourceserver.constant.StockAnalyzerConstant;

/**
 * - Supports both HTTP and HTTPS - Uses a connection pool to re-use connections
 * and save overhead of creating connections. - Has a custom connection
 * keep-alive strategy (to apply a default keep-alive if one isn't specified) -
 * Starts an idle connection monitor to continuously clean up stale connections.
 */
@Configuration
@EnableScheduling
public class HttpClientConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientConfig.class);

	
	@Bean
	public PoolingHttpClientConnectionManager poolingConnectionManager() {

		PoolingHttpClientConnectionManager poolingConnectionManager = null;
		try {
			SSLContextBuilder builder = new SSLContextBuilder();
			builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());

			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
					.register("https", sslsf).register("http", new PlainConnectionSocketFactory()).build();

			poolingConnectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
			poolingConnectionManager.setMaxTotal(StockAnalyzerConstant.MAX_TOTAL_CONNECTIONS);
		} catch (KeyManagementException | KeyStoreException | NoSuchAlgorithmException e) {
			LOGGER.error("Pooling Connection Manager Initialisation failure because of " + e.getMessage(), e);
		}
		return poolingConnectionManager;
	}

	@Bean
	public ConnectionKeepAliveStrategy connectionKeepAliveStrategy() {
		return new ConnectionKeepAliveStrategy() {
			@Override
			public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
				HeaderElementIterator it = new BasicHeaderElementIterator(
						response.headerIterator(HTTP.CONN_KEEP_ALIVE));
				while (it.hasNext()) {
					HeaderElement he = it.nextElement();
					String param = he.getName();
					String value = he.getValue();

					if (value != null && param.equalsIgnoreCase("timeout")) {
						return Long.parseLong(value) * 1000;
					}
				}
				return StockAnalyzerConstant.DEFAULT_KEEP_ALIVE_TIME_MILLIS;
			}
		};
	}

	@Bean
	public CloseableHttpClient httpClient() {
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(StockAnalyzerConstant.REQUEST_TIMEOUT)
				.setConnectTimeout(StockAnalyzerConstant.CONNECT_TIMEOUT).setSocketTimeout(StockAnalyzerConstant.SOCKET_TIMEOUT).build();

		return HttpClients.custom().setDefaultRequestConfig(requestConfig)
				.setConnectionManager(poolingConnectionManager()).setKeepAliveStrategy(connectionKeepAliveStrategy())
				.build();
	}

	@Bean
	public Runnable idleConnectionMonitor(final PoolingHttpClientConnectionManager connectionManager) {
		return new Runnable() {
			@Override
			@Scheduled(fixedDelay = 10000)
			public void run() {
				try {
					if (connectionManager != null) {
						LOGGER.trace("run IdleConnectionMonitor - Closing expired and idle connections...");
						connectionManager.closeExpiredConnections();
						connectionManager.closeIdleConnections(StockAnalyzerConstant.CLOSE_IDLE_CONNECTION_WAIT_TIME_SECS, TimeUnit.SECONDS);
					} else {
						LOGGER.trace("run IdleConnectionMonitor - Http Client Connection manager is not initialised");
					}
				} catch (Exception e) {
					LOGGER.error("run IdleConnectionMonitor - Exception occurred. msg={}, e={}", e.getMessage(), e);
				}
			}
		};
	}

}
