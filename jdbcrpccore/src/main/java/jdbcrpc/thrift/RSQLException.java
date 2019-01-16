/**
 * Autogenerated by Thrift Compiler (0.9.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package jdbcrpc.thrift;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2015-5-7")
public class RSQLException extends TException implements org.apache.thrift.TBase<RSQLException, RSQLException._Fields>, java.io.Serializable, Cloneable, Comparable<RSQLException> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("RSQLException");

  private static final org.apache.thrift.protocol.TField REASON_FIELD_DESC = new org.apache.thrift.protocol.TField("reason", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField SQL_STATE_FIELD_DESC = new org.apache.thrift.protocol.TField("sqlState", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField VENDOR_CODE_FIELD_DESC = new org.apache.thrift.protocol.TField("vendorCode", org.apache.thrift.protocol.TType.I32, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new RSQLExceptionStandardSchemeFactory());
    schemes.put(TupleScheme.class, new RSQLExceptionTupleSchemeFactory());
  }

  public String reason; // required
  public String sqlState; // required
  public int vendorCode; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    REASON((short)1, "reason"),
    SQL_STATE((short)2, "sqlState"),
    VENDOR_CODE((short)3, "vendorCode");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // REASON
          return REASON;
        case 2: // SQL_STATE
          return SQL_STATE;
        case 3: // VENDOR_CODE
          return VENDOR_CODE;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __VENDORCODE_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.REASON, new org.apache.thrift.meta_data.FieldMetaData("reason", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.SQL_STATE, new org.apache.thrift.meta_data.FieldMetaData("sqlState", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.VENDOR_CODE, new org.apache.thrift.meta_data.FieldMetaData("vendorCode", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(RSQLException.class, metaDataMap);
  }

  public RSQLException() {
  }

  public RSQLException(
    String reason,
    String sqlState,
    int vendorCode)
  {
    this();
    this.reason = reason;
    this.sqlState = sqlState;
    this.vendorCode = vendorCode;
    setVendorCodeIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public RSQLException(RSQLException other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetReason()) {
      this.reason = other.reason;
    }
    if (other.isSetSqlState()) {
      this.sqlState = other.sqlState;
    }
    this.vendorCode = other.vendorCode;
  }

  public RSQLException deepCopy() {
    return new RSQLException(this);
  }

  @Override
  public void clear() {
    this.reason = null;
    this.sqlState = null;
    setVendorCodeIsSet(false);
    this.vendorCode = 0;
  }

  public String getReason() {
    return this.reason;
  }

  public RSQLException setReason(String reason) {
    this.reason = reason;
    return this;
  }

  public void unsetReason() {
    this.reason = null;
  }

  /** Returns true if field reason is set (has been assigned a value) and false otherwise */
  public boolean isSetReason() {
    return this.reason != null;
  }

  public void setReasonIsSet(boolean value) {
    if (!value) {
      this.reason = null;
    }
  }

  public String getSqlState() {
    return this.sqlState;
  }

  public RSQLException setSqlState(String sqlState) {
    this.sqlState = sqlState;
    return this;
  }

  public void unsetSqlState() {
    this.sqlState = null;
  }

  /** Returns true if field sqlState is set (has been assigned a value) and false otherwise */
  public boolean isSetSqlState() {
    return this.sqlState != null;
  }

  public void setSqlStateIsSet(boolean value) {
    if (!value) {
      this.sqlState = null;
    }
  }

  public int getVendorCode() {
    return this.vendorCode;
  }

  public RSQLException setVendorCode(int vendorCode) {
    this.vendorCode = vendorCode;
    setVendorCodeIsSet(true);
    return this;
  }

  public void unsetVendorCode() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __VENDORCODE_ISSET_ID);
  }

  /** Returns true if field vendorCode is set (has been assigned a value) and false otherwise */
  public boolean isSetVendorCode() {
    return EncodingUtils.testBit(__isset_bitfield, __VENDORCODE_ISSET_ID);
  }

  public void setVendorCodeIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __VENDORCODE_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case REASON:
      if (value == null) {
        unsetReason();
      } else {
        setReason((String)value);
      }
      break;

    case SQL_STATE:
      if (value == null) {
        unsetSqlState();
      } else {
        setSqlState((String)value);
      }
      break;

    case VENDOR_CODE:
      if (value == null) {
        unsetVendorCode();
      } else {
        setVendorCode((Integer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case REASON:
      return getReason();

    case SQL_STATE:
      return getSqlState();

    case VENDOR_CODE:
      return Integer.valueOf(getVendorCode());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case REASON:
      return isSetReason();
    case SQL_STATE:
      return isSetSqlState();
    case VENDOR_CODE:
      return isSetVendorCode();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof RSQLException)
      return this.equals((RSQLException)that);
    return false;
  }

  public boolean equals(RSQLException that) {
    if (that == null)
      return false;

    boolean this_present_reason = true && this.isSetReason();
    boolean that_present_reason = true && that.isSetReason();
    if (this_present_reason || that_present_reason) {
      if (!(this_present_reason && that_present_reason))
        return false;
      if (!this.reason.equals(that.reason))
        return false;
    }

    boolean this_present_sqlState = true && this.isSetSqlState();
    boolean that_present_sqlState = true && that.isSetSqlState();
    if (this_present_sqlState || that_present_sqlState) {
      if (!(this_present_sqlState && that_present_sqlState))
        return false;
      if (!this.sqlState.equals(that.sqlState))
        return false;
    }

    boolean this_present_vendorCode = true;
    boolean that_present_vendorCode = true;
    if (this_present_vendorCode || that_present_vendorCode) {
      if (!(this_present_vendorCode && that_present_vendorCode))
        return false;
      if (this.vendorCode != that.vendorCode)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_reason = true && (isSetReason());
    list.add(present_reason);
    if (present_reason)
      list.add(reason);

    boolean present_sqlState = true && (isSetSqlState());
    list.add(present_sqlState);
    if (present_sqlState)
      list.add(sqlState);

    boolean present_vendorCode = true;
    list.add(present_vendorCode);
    if (present_vendorCode)
      list.add(vendorCode);

    return list.hashCode();
  }

  @Override
  public int compareTo(RSQLException other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetReason()).compareTo(other.isSetReason());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetReason()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.reason, other.reason);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetSqlState()).compareTo(other.isSetSqlState());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSqlState()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.sqlState, other.sqlState);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetVendorCode()).compareTo(other.isSetVendorCode());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetVendorCode()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.vendorCode, other.vendorCode);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("RSQLException(");
    boolean first = true;

    sb.append("reason:");
    if (this.reason == null) {
      sb.append("null");
    } else {
      sb.append(this.reason);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("sqlState:");
    if (this.sqlState == null) {
      sb.append("null");
    } else {
      sb.append(this.sqlState);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("vendorCode:");
    sb.append(this.vendorCode);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class RSQLExceptionStandardSchemeFactory implements SchemeFactory {
    public RSQLExceptionStandardScheme getScheme() {
      return new RSQLExceptionStandardScheme();
    }
  }

  private static class RSQLExceptionStandardScheme extends StandardScheme<RSQLException> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, RSQLException struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // REASON
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.reason = iprot.readString();
              struct.setReasonIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // SQL_STATE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.sqlState = iprot.readString();
              struct.setSqlStateIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // VENDOR_CODE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.vendorCode = iprot.readI32();
              struct.setVendorCodeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, RSQLException struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.reason != null) {
        oprot.writeFieldBegin(REASON_FIELD_DESC);
        oprot.writeString(struct.reason);
        oprot.writeFieldEnd();
      }
      if (struct.sqlState != null) {
        oprot.writeFieldBegin(SQL_STATE_FIELD_DESC);
        oprot.writeString(struct.sqlState);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(VENDOR_CODE_FIELD_DESC);
      oprot.writeI32(struct.vendorCode);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class RSQLExceptionTupleSchemeFactory implements SchemeFactory {
    public RSQLExceptionTupleScheme getScheme() {
      return new RSQLExceptionTupleScheme();
    }
  }

  private static class RSQLExceptionTupleScheme extends TupleScheme<RSQLException> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, RSQLException struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetReason()) {
        optionals.set(0);
      }
      if (struct.isSetSqlState()) {
        optionals.set(1);
      }
      if (struct.isSetVendorCode()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetReason()) {
        oprot.writeString(struct.reason);
      }
      if (struct.isSetSqlState()) {
        oprot.writeString(struct.sqlState);
      }
      if (struct.isSetVendorCode()) {
        oprot.writeI32(struct.vendorCode);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, RSQLException struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.reason = iprot.readString();
        struct.setReasonIsSet(true);
      }
      if (incoming.get(1)) {
        struct.sqlState = iprot.readString();
        struct.setSqlStateIsSet(true);
      }
      if (incoming.get(2)) {
        struct.vendorCode = iprot.readI32();
        struct.setVendorCodeIsSet(true);
      }
    }
  }

}

