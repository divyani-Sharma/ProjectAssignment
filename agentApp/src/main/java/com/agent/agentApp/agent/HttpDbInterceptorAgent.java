package com.agent.agentApp.agent;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

public class HttpDbInterceptorAgent {

	public static void premain(String agentArgs, Instrumentation inst) {
        String mode = System.getenv("HT_MODE");

        if ("RECORD".equalsIgnoreCase(mode)) {
            setupRecording(inst);
        } else if ("REPLAY".equalsIgnoreCase(mode)) {
            setupReplaying(inst);
        }
    }

    private static void setupRecording(Instrumentation inst) {
        new AgentBuilder.Default()
                .type(ElementMatchers.named("org.springframework.jdbc.core.JdbcTemplate"))
                .transform((builder, typeDescription, classLoader, module, protectionDomain) ->
                        builder.method(ElementMatchers.named("update"))
                                .intercept(MethodDelegation.to(RecordDbInterceptor.class))
                ).installOn(inst);

        new AgentBuilder.Default()
                .type(ElementMatchers.named("org.springframework.web.client.RestTemplate"))
                .transform((builder, typeDescription, classLoader, module, protectionDomain) ->
                        builder.method(ElementMatchers.named("getForEntity"))
                                .intercept(MethodDelegation.to(RecordHttpInterceptor.class))
                ).installOn(inst);
    }

    private static void setupReplaying(Instrumentation inst) {
        new AgentBuilder.Default()
                .type(ElementMatchers.named("org.springframework.jdbc.core.JdbcTemplate"))
                .transform((builder, typeDescription, classLoader, module, protectionDomain) ->
                        builder.method(ElementMatchers.named("update"))
                                .intercept(MethodDelegation.to(ReplayDbInterceptor.class))
                ).installOn(inst);

        new AgentBuilder.Default()
                .type(ElementMatchers.named("org.springframework.web.client.RestTemplate"))
                .transform((builder, typeDescription, classLoader, module, protectionDomain) ->
                        builder.method(ElementMatchers.named("getForEntity"))
                                .intercept(MethodDelegation.to(ReplayHttpInterceptor.class))
                ).installOn(inst);
    }
}
