package com.juapk.frame.util;

import java.io.Serializable;

/**
 * @author HUBIN
 * @version 1.0
 * @since 2011-11-29
 */
public class UUIDHexGenerator
    extends UUIDGenerator {
  private String sep = "";

  protected String format(int intval) {
    String formatted = Integer.toHexString(intval);
    StringBuffer buf = new StringBuffer("00000000");
    buf.replace(8 - formatted.length(), 8, formatted);
    return buf.toString();
  }

  protected String format(short shortval) {
    String formatted = Integer.toHexString(shortval);
    StringBuffer buf = new StringBuffer("0000");
    buf.replace(4 - formatted.length(), 4, formatted);
    return buf.toString();
  }

  public Serializable generate() {
    return new StringBuffer(20)
//		.append( format( getIP() ) ).append(sep)
        .append(format(getJVM())).append(sep)
//		.append( format( getHiTime() ) ).append(sep)
        .append(format(getLoTime())).append(sep)
        .append(format(getCount()))
        .toString();
  }
}
