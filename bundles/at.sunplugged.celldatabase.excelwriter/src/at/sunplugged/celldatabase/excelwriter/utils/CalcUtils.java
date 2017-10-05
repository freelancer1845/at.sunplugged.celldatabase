package at.sunplugged.celldatabase.excelwriter.utils;

import java.util.List;
import java.util.stream.DoubleStream;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class CalcUtils {

  public static <T extends EObject> double mean(List<T> objects, EStructuralFeature feature) {
    if (feature.getEType()
        .getName()
        .equals("EDouble")) {

      return objects.stream()
          .flatMapToDouble(object -> DoubleStream.of((double) object.eGet(feature)))
          .average()
          .getAsDouble();

    } else {
      throw new IllegalArgumentException(feature.getEType()
          .getName() + " is not supported...");
    }



  }



  private CalcUtils() {

  }
}
