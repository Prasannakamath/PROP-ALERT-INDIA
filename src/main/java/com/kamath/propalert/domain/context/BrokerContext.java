package com.kamath.propalert.domain.context;

/**
 * Pure Domain component to hold the current Broker's identity.
 */
public class BrokerContext {
    private static final ThreadLocal<String> CURRENT_BROKER = new InheritableThreadLocal<>();

    public static void setBrokerId(String brokerId) {
        CURRENT_BROKER.set(brokerId);
    }

    public static String getBrokerId() {
        return CURRENT_BROKER.get();
    }

    public static void clear() {
        CURRENT_BROKER.remove();
    }
}
