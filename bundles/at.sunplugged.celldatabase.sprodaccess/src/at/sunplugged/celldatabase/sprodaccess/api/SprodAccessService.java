package at.sunplugged.celldatabase.sprodaccess.api;

import java.io.File;

public interface SprodAccessService {


  void openFile(File file) throws SprodAccessException;

  void close();
}
