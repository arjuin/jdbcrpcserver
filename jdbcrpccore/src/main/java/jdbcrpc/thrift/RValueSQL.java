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
public class RValueSQL implements org.apache.thrift.TBase<RValueSQL, RValueSQL._Fields>, java.io.Serializable, Cloneable, Comparable<RValueSQL> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("RValueSQL");

  private static final org.apache.thrift.protocol.TField ISNULL_FIELD_DESC = new org.apache.thrift.protocol.TField("isnull", org.apache.thrift.protocol.TType.BOOL, (short)1);
  private static final org.apache.thrift.protocol.TField VAL_FIELD_DESC = new org.apache.thrift.protocol.TField("val", org.apache.thrift.protocol.TType.STRUCT, (short)2);
  private static final org.apache.thrift.protocol.TField SQL_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("sqlType", org.apache.thrift.protocol.TType.I32, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new RValueSQLStandardSchemeFactory());
    schemes.put(TupleScheme.class, new RValueSQLTupleSchemeFactory());
  }

  public boolean isnull; // required
  public RawVal val; // required
  public int sqlType; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ISNULL((short)1, "isnull"),
    VAL((short)2, "val"),
    SQL_TYPE((short)3, "sqlType");

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
        case 1: // ISNULL
          return ISNULL;
        case 2: // VAL
          return VAL;
        case 3: // SQL_TYPE
          return SQL_TYPE;
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
  private static final int __ISNULL_ISSET_ID = 0;
  private static final int __SQLTYPE_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ISNULL, new org.apache.thrift.meta_data.FieldMetaData("isnull", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.VAL, new org.apache.thrift.meta_data.FieldMetaData("val", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, RawVal.class)));
    tmpMap.put(_Fields.SQL_TYPE, new org.apache.thrift.meta_data.FieldMetaData("sqlType", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(RValueSQL.class, metaDataMap);
  }

  public RValueSQL() {
  }

  public RValueSQL(
    boolean isnull,
    RawVal val,
    int sqlType)
  {
    this();
    this.isnull = isnull;
    setIsnullIsSet(true);
    this.val = val;
    this.sqlType = sqlType;
    setSqlTypeIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public RValueSQL(RValueSQL other) {
    __isset_bitfield = other.__isset_bitfield;
    this.isnull = other.isnull;
    if (other.isSetVal()) {
      this.val = new RawVal(other.val);
    }
    this.sqlType = other.sqlType;
  }

  public RValueSQL deepCopy() {
    return new RValueSQL(this);
  }

  @Override
  public void clear() {
    setIsnullIsSet(false);
    this.isnull = false;
    this.val = null;
    setSqlTypeIsSet(false);
    this.sqlType = 0;
  }

  public boolean isIsnull() {
    return this.isnull;
  }

  public RValueSQL setIsnull(boolean isnull) {
    this.isnull = isnull;
    setIsnullIsSet(true);
    return this;
  }

  public void unsetIsnull() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ISNULL_ISSET_ID);
  }

  /** Returns true if field isnull is set (has been assigned a value) and false otherwise */
  public boolean isSetIsnull() {
    return EncodingUtils.testBit(__isset_bitfield, __ISNULL_ISSET_ID);
  }

  public void setIsnullIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ISNULL_ISSET_ID, value);
  }

  public RawVal getVal() {
    return this.val;
  }

  public RValueSQL setVal(RawVal val) {
    this.val = val;
    return this;
  }

  public void unsetVal() {
    this.val = null;
  }

  /** Returns true if field val is set (has been assigned a value) and false otherwise */
  public boolean isSetVal() {
    return this.val != null;
  }

  public void setValIsSet(boolean value) {
    if (!value) {
      this.val = null;
    }
  }

  public int getSqlType() {
    return this.sqlType;
  }

  public RValueSQL setSqlType(int sqlType) {
    this.sqlType = sqlType;
    setSqlTypeIsSet(true);
    return this;
  }

  public void unsetSqlType() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __SQLTYPE_ISSET_ID);
  }

  /** Returns true if field sqlType is set (has been assigned a value) and false otherwise */
  public boolean isSetSqlType() {
    return EncodingUtils.testBit(__isset_bitfield, __SQLTYPE_ISSET_ID);
  }

  public void setSqlTypeIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __SQLTYPE_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case ISNULL:
      if (value == null) {
        unsetIsnull();
      } else {
        setIsnull((Boolean)value);
      }
      break;

    case VAL:
      if (value == null) {
        unsetVal();
      } else {
        setVal((RawVal)value);
      }
      break;

    case SQL_TYPE:
      if (value == null) {
        unsetSqlType();
      } else {
        setSqlType((Integer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ISNULL:
      return Boolean.valueOf(isIsnull());

    case VAL:
      return getVal();

    case SQL_TYPE:
      return Integer.valueOf(getSqlType());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ISNULL:
      return isSetIsnull();
    case VAL:
      return isSetVal();
    case SQL_TYPE:
      return isSetSqlType();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof RValueSQL)
      return this.equals((RValueSQL)that);
    return false;
  }

  public boolean equals(RValueSQL that) {
    if (that == null)
      return false;

    boolean this_present_isnull = true;
    boolean that_present_isnull = true;
    if (this_present_isnull || that_present_isnull) {
      if (!(this_present_isnull && that_present_isnull))
        return false;
      if (this.isnull != that.isnull)
        return false;
    }

    boolean this_present_val = true && this.isSetVal();
    boolean that_present_val = true && that.isSetVal();
    if (this_present_val || that_present_val) {
      if (!(this_present_val && that_present_val))
        return false;
      if (!this.val.equals(that.val))
        return false;
    }

    boolean this_present_sqlType = true;
    boolean that_present_sqlType = true;
    if (this_present_sqlType || that_present_sqlType) {
      if (!(this_present_sqlType && that_present_sqlType))
        return false;
      if (this.sqlType != that.sqlType)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_isnull = true;
    list.add(present_isnull);
    if (present_isnull)
      list.add(isnull);

    boolean present_val = true && (isSetVal());
    list.add(present_val);
    if (present_val)
      list.add(val);

    boolean present_sqlType = true;
    list.add(present_sqlType);
    if (present_sqlType)
      list.add(sqlType);

    return list.hashCode();
  }

  @Override
  public int compareTo(RValueSQL other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetIsnull()).compareTo(other.isSetIsnull());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIsnull()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.isnull, other.isnull);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetVal()).compareTo(other.isSetVal());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetVal()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.val, other.val);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetSqlType()).compareTo(other.isSetSqlType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSqlType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.sqlType, other.sqlType);
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
    StringBuilder sb = new StringBuilder("RValueSQL(");
    boolean first = true;

    sb.append("isnull:");
    sb.append(this.isnull);
    first = false;
    if (!first) sb.append(", ");
    sb.append("val:");
    if (this.val == null) {
      sb.append("null");
    } else {
      sb.append(this.val);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("sqlType:");
    sb.append(this.sqlType);
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

  private static class RValueSQLStandardSchemeFactory implements SchemeFactory {
    public RValueSQLStandardScheme getScheme() {
      return new RValueSQLStandardScheme();
    }
  }

  private static class RValueSQLStandardScheme extends StandardScheme<RValueSQL> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, RValueSQL struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ISNULL
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.isnull = iprot.readBool();
              struct.setIsnullIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // VAL
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.val = new RawVal();
              struct.val.read(iprot);
              struct.setValIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // SQL_TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.sqlType = iprot.readI32();
              struct.setSqlTypeIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, RValueSQL struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(ISNULL_FIELD_DESC);
      oprot.writeBool(struct.isnull);
      oprot.writeFieldEnd();
      if (struct.val != null) {
        oprot.writeFieldBegin(VAL_FIELD_DESC);
        struct.val.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(SQL_TYPE_FIELD_DESC);
      oprot.writeI32(struct.sqlType);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class RValueSQLTupleSchemeFactory implements SchemeFactory {
    public RValueSQLTupleScheme getScheme() {
      return new RValueSQLTupleScheme();
    }
  }

  private static class RValueSQLTupleScheme extends TupleScheme<RValueSQL> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, RValueSQL struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetIsnull()) {
        optionals.set(0);
      }
      if (struct.isSetVal()) {
        optionals.set(1);
      }
      if (struct.isSetSqlType()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetIsnull()) {
        oprot.writeBool(struct.isnull);
      }
      if (struct.isSetVal()) {
        struct.val.write(oprot);
      }
      if (struct.isSetSqlType()) {
        oprot.writeI32(struct.sqlType);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, RValueSQL struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.isnull = iprot.readBool();
        struct.setIsnullIsSet(true);
      }
      if (incoming.get(1)) {
        struct.val = new RawVal();
        struct.val.read(iprot);
        struct.setValIsSet(true);
      }
      if (incoming.get(2)) {
        struct.sqlType = iprot.readI32();
        struct.setSqlTypeIsSet(true);
      }
    }
  }

}
