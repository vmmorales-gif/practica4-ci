package org.ejemplo.musica;


import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import java.io.PrintWriter;

/**
 * Runner alternativo para lanzar los tests desde una main.
 * Útil para capturar la salida por consola (mvn/terminal-like).
 */
public class TestRunner {
    public static void main(String[] args) {
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(DiscoverySelectors.selectClass(ServicioMusicaTests.class))
                .build();

        SummaryGeneratingListener listener = new SummaryGeneratingListener();

        Launcher launcher = LauncherFactory.create();
        launcher.registerTestExecutionListeners(listener);
        launcher.execute(request);

        TestExecutionSummary summary = listener.getSummary();
        // imprime resumen legible por consola
        summary.printTo(new PrintWriter(System.out));

        // imprime recuento breve
        System.out.println("\nResumen corto: total=" + summary.getTestsFoundCount() +
                " executed=" + summary.getTestsSucceededCount() +
                " failed=" + summary.getTotalFailureCount());

        // salida con código distinto de 0 si hay fallos (útil para CI)
        System.exit(summary.getTotalFailureCount() == 0 ? 0 : 1);
    }
}