package com.msref.karyonjersey.main;

import java.io.IOException;

import netflix.karyon.Karyon;

import com.msref.karyonjersey.module.BootstrapInjectionModule;
import com.netflix.governator.guice.BootstrapModule;

public class KaryonJerseyRunner {

	public static void main(String[] args) throws IOException {
		Karyon.forApplication(BootstrapInjectionModule.class, (BootstrapModule[]) null).startAndWaitTillShutdown();
	}	

}
