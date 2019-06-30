package com.msref.karyonjersey.module;

import netflix.adminresources.resources.KaryonWebAdminModule;
import netflix.karyon.KaryonBootstrap;
import netflix.karyon.ShutdownModule;
import netflix.karyon.archaius.ArchaiusBootstrap;
import netflix.karyon.jersey.blocking.KaryonJerseyModule;
import netflix.karyon.servo.KaryonServoModule;

import com.msref.karyonjersey.heath.HealthCheck;
import com.netflix.governator.annotations.Modules;

@ArchaiusBootstrap
@KaryonBootstrap(name = "wos-links-service", healthcheck = HealthCheck.class)
@Modules(include = {
        ShutdownModule.class,
        KaryonWebAdminModule.class,
        BootstrapInjectionModule.KaryonJerseyRouterModuleImpl.class,
        KaryonServoModule.class
})
public interface BootstrapInjectionModule {

	class KaryonJerseyRouterModuleImpl extends KaryonJerseyModule {
		@Override
		protected void configureServer() {
            server().port(8888).threadPoolSize(100);
		}
	}
}
