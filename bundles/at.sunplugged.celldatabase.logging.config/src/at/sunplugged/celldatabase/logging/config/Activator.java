package at.sunplugged.celldatabase.logging.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.service.datalocation.Location;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

public class Activator implements BundleActivator {

  private static BundleContext context;

  static BundleContext getContext() {
    return context;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
   */
  public void start(BundleContext bundleContext) throws Exception {
    Activator.context = bundleContext;
    configureLogbackInBundle(bundleContext.getBundle());
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
   */
  public void stop(BundleContext bundleContext) throws Exception {
    Activator.context = null;
  }

  private void configureLogbackInBundle(Bundle bundle) throws JoranException, IOException {
    LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
    JoranConfigurator jc = new JoranConfigurator();
    jc.setContext(context);
    context.reset();

    // this assumes that the logback.xml file is in the root of the bundle.

    // File config = new File(
    // bundle.getEntry("../../features/at.sunplugged.celldatabase.feature/rootfiles/logback.xml")
    // .toString());
    // if (config.exists()) {
    // jc.doConfigure(config);
    // } else {
    Location installLocation = Platform.getInstallLocation();
    String installPath = installLocation.getURL().getPath().replaceAll("^/", "");
    File file = Paths.get(installPath, "logback.xml").toFile();
    if (file.exists()) {
      jc.doConfigure(file);
    } else {
      jc.doConfigure(FileLocator.openStream(bundle, new Path("logback.xml"), false));
    }

    // }

  }

}
