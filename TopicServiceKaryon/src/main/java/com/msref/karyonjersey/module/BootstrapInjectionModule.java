package com.msref.karyonjersey.module;

import netflix.adminresources.resources.KaryonWebAdminModule;
import netflix.karyon.KaryonBootstrap;
import netflix.karyon.archaius.ArchaiusBootstrap;
import netflix.karyon.eureka.KaryonEurekaModule;
import netflix.karyon.jersey.blocking.KaryonJerseyModule;
import netflix.karyon.servo.KaryonServoModule;

import com.msref.karyonjersey.heath.HealthCheck;
import com.netflix.governator.annotations.Modules;

@ArchaiusBootstrap
@KaryonBootstrap(name = "topic-service", healthcheck = HealthCheck.class)
@Modules(include = {
        //ShutdownModule.class, I commented this module as it starts a RX server at 7002 port. This conflicts with the course service which uses the same port. 
		// I'm not able to find how to change this port number which starts a RX TCP server
        KaryonWebAdminModule.class,
        KaryonEurekaModule.class,
        BootstrapInjectionModule.KaryonJerseyRouterModuleImpl.class,
        KaryonServoModule.class
})
public interface BootstrapInjectionModule {

	class KaryonJerseyRouterModuleImpl extends KaryonJerseyModule {
		@Override
		protected void configureServer() {
            server().port(8889).threadPoolSize(100);
		}
	}
}
