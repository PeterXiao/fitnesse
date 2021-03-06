// Modified or written by Object Mentor, Inc. for inclusion with FitNesse.
// Copyright (c) 2002 Cunningham & Cunningham, Inc.
// Released under the terms of the GNU General Public License version 2 or later.
package fit;

// Copyright (c) 2002 Cunningham & Cunningham, Inc.
// Released under the terms of the GNU General Public License version 2 or later.

// Warning: not (yet) a general number usable in all calculations.

public class ScientificDouble extends Number implements Comparable<Number> {
  private static final long serialVersionUID = 1L;

  protected final double value;
  protected double precision;

  public ScientificDouble(double value) {
    this.value = value;
    this.precision = 0;
  }

  public static ScientificDouble valueOf(String s) {
    ScientificDouble result = new ScientificDouble(Double.parseDouble(s));
    result.precision = precision(s);
    return result;
  }

  public static ScientificDouble parse(String s) {
    return valueOf(s);
  }

  public static double precision(String s) {
    double value = Double.parseDouble(s);
    double bound = Double.parseDouble(tweak(s.trim()));
    return Math.abs(bound - value);
  }

  public static String tweak(String s) {
    int pos;
    if ((pos = s.toLowerCase().indexOf("e")) >= 0) {
      return tweak(s.substring(0, pos)) + s.substring(pos);
    }
    if (s.contains(".")) {
      return s + "5";
    }
    return s + ".5";
  }

  @Override
  public boolean equals(Object obj) {
    return obj != null && obj instanceof Number && compareTo((Number) obj) == 0;
  }

  @Override
  public int hashCode() {
    return (int) value;
  }

  @Override
  public int compareTo(Number obj) {
    double other = obj.doubleValue();
    double diff = value - other;
    if (diff < -precision) return -1;
    if (diff > precision) return 1;
    if (Double.isNaN(value) && Double.isNaN(other)) return 0;
    if (Double.isNaN(value)) return 1;
    if (Double.isNaN(other)) return -1;
    return 0;
  }

  @Override
  public String toString() {
    return Double.toString(value);
  }

  @Override
  public double doubleValue() {
    return value;
  }

  @Override
  public float floatValue() {
    return (float) value;
  }

  @Override
  public long longValue() {
    return (long) value;
  }

  @Override
  public int intValue() {
    return (int) value;
  }
}
