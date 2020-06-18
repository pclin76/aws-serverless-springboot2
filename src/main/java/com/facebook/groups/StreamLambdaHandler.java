package com.facebook.groups;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.serverless.proxy.spring.SpringBootProxyHandlerBuilder;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamLambdaHandler implements RequestStreamHandler {

	private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> springBootLambdaContainerHandler;

	//static {
	//try {
	//	springBootLambdaContainerHandler = SpringBootLambdaContainerHandler.getAwsProxyHandler(Application.class);
	//} catch (ContainerInitializationException e) {
	//	// if we fail here. We re-throw the exception to force another cold start
	//	e.printStackTrace();
	//	throw new RuntimeException("Could not initialize Spring Boot application", e);
	//}
	//}
//
	/**
	 * For applications that take longer than 10 seconds to start, use the
	 * async builder:
	 *
	 * @throws
	 * com.amazonaws.serverless.exceptions.ContainerInitializationException
	 */
	public StreamLambdaHandler() throws ContainerInitializationException {
		//long startTime = Instant.now().toEpochMilli();
		springBootLambdaContainerHandler = new SpringBootProxyHandlerBuilder<AwsProxyRequest>().
			defaultProxy().
			//asyncInit(startTime).
			asyncInit().
			springBootApplication(Application.class).
			buildAndInitialize();
	}

	@Override
	public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
		springBootLambdaContainerHandler.proxyStream(inputStream, outputStream, context);
	}
}
