package at.sunplugged.celldatabase.sprodaccess.impl;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import at.sunplugged.celldatabase.sprodaccess.api.SprodAccessException;
import at.sunplugged.celldatabase.sprodaccess.api.SprodAccessService;


public class SprodAccessServiceImpl implements SprodAccessService {

  private static final Logger LOG = LoggerFactory.getLogger(SprodAccessServiceImpl.class);

  private Connection connection;

  @Override
  public void openFile(File file) throws SprodAccessException {
    LOG.debug("Opening Microsoft Access File: " + file.getAbsolutePath());

    try {
      connection = getUcanaccessConnection(file.getAbsolutePath());
    } catch (SQLException e) {
      LOG.error("SQLException: " + e.getMessage());
      throw new SprodAccessException("Failed to open File: " + file.getAbsolutePath(), e);
    }

  }

  @Override
  public void close() {
    LOG.debug("Closing Microsoft Access connection...");
  }

  private Connection getUcanaccessConnection(String pathToDb) throws SQLException {
    String url = "jdbc:ucanaccess://" + pathToDb;
    return DriverManager.getConnection(url);
  }

}
