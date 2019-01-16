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
public class ArrayVal implements org.apache.thrift.TBase<ArrayVal, ArrayVal._Fields>, java.io.Serializable, Cloneable, Comparable<ArrayVal> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ArrayVal");

  private static final org.apache.thrift.protocol.TField SQL_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("sqlType", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField ELEMENTS_FIELD_DESC = new org.apache.thrift.protocol.TField("elements", org.apache.thrift.protocol.TType.LIST, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new ArrayValStandardSchemeFactory());
    schemes.put(TupleScheme.class, new ArrayValTupleSchemeFactory());
  }

  public int sqlType; // required
  public List<RawVal> elements; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    SQL_TYPE((short)1, "sqlType"),
    ELEMENTS((short)2, "elements");

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
        case 1: // SQL_TYPE
          return SQL_TYPE;
        case 2: // ELEMENTS
          return ELEMENTS;
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
  private static final int __SQLTYPE_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.SQL_TYPE, new org.apache.thrift.meta_data.FieldMetaData("sqlType", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.ELEMENTS, new org.apache.thrift.meta_data.FieldMetaData("elements", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, RawVal.class))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ArrayVal.class, metaDataMap);
  }

  public ArrayVal() {
  }

  public ArrayVal(
    int sqlType,
    List<RawVal> elements)
  {
    this();
    this.sqlType = sqlType;
    setSqlTypeIsSet(true);
    this.elements = elements;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ArrayVal(ArrayVal other) {
    __isset_bitfield = other.__isset_bitfield;
    this.sqlType = other.sqlType;
    if (other.isSetElements()) {
      List<RawVal> __this__elements = new ArrayList<RawVal>(other.elements.size());
      for (RawVal other_element : other.elements) {
        __this__elements.add(new RawVal(other_element));
      }
      this.elements = __this__elements;
    }
  }

  public ArrayVal deepCopy() {
    return new ArrayVal(this);
  }

  @Override
  public void clear() {
    setSqlTypeIsSet(false);
    this.sqlType = 0;
    this.elements = null;
  }

  public int getSqlType() {
    return this.sqlType;
  }

  public ArrayVal setSqlType(int sqlType) {
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

  public int getElementsSize() {
    return (this.elements == null) ? 0 : this.elements.size();
  }

  public java.util.Iterator<RawVal> getElementsIterator() {
    return (this.elements == null) ? null : this.elements.iterator();
  }

  public void addToElements(RawVal elem) {
    if (this.elements == null) {
      this.elements = new ArrayList<RawVal>();
    }
    this.elements.add(elem);
  }

  public List<RawVal> getElements() {
    return this.elements;
  }

  public ArrayVal setElements(List<RawVal> elements) {
    this.elements = elements;
    return this;
  }

  public void unsetElements() {
    this.elements = null;
  }

  /** Returns true if field elements is set (has been assigned a value) and false otherwise */
  public boolean isSetElements() {
    return this.elements != null;
  }

  public void setElementsIsSet(boolean value) {
    if (!value) {
      this.elements = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case SQL_TYPE:
      if (value == null) {
        unsetSqlType();
      } else {
        setSqlType((Integer)value);
      }
      break;

    case ELEMENTS:
      if (value == null) {
        unsetElements();
      } else {
        setElements((List<RawVal>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case SQL_TYPE:
      return Integer.valueOf(getSqlType());

    case ELEMENTS:
      return getElements();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case SQL_TYPE:
      return isSetSqlType();
    case ELEMENTS:
      return isSetElements();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ArrayVal)
      return this.equals((ArrayVal)that);
    return false;
  }

  public boolean equals(ArrayVal that) {
    if (that == null)
      return false;

    boolean this_present_sqlType = true;
    boolean that_present_sqlType = true;
    if (this_present_sqlType || that_present_sqlType) {
      if (!(this_present_sqlType && that_present_sqlType))
        return false;
      if (this.sqlType != that.sqlType)
        return false;
    }

    boolean this_present_elements = true && this.isSetElements();
    boolean that_present_elements = true && that.isSetElements();
    if (this_present_elements || that_present_elements) {
      if (!(this_present_elements && that_present_elements))
        return false;
      if (!this.elements.equals(that.elements))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_sqlType = true;
    list.add(present_sqlType);
    if (present_sqlType)
      list.add(sqlType);

    boolean present_elements = true && (isSetElements());
    list.add(present_elements);
    if (present_elements)
      list.add(elements);

    return list.hashCode();
  }

  @Override
  public int compareTo(ArrayVal other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

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
    lastComparison = Boolean.valueOf(isSetElements()).compareTo(other.isSetElements());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetElements()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.elements, other.elements);
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
    StringBuilder sb = new StringBuilder("ArrayVal(");
    boolean first = true;

    sb.append("sqlType:");
    sb.append(this.sqlType);
    first = false;
    if (!first) sb.append(", ");
    sb.append("elements:");
    if (this.elements == null) {
      sb.append("null");
    } else {
      sb.append(this.elements);
    }
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

  private static class ArrayValStandardSchemeFactory implements SchemeFactory {
    public ArrayValStandardScheme getScheme() {
      return new ArrayValStandardScheme();
    }
  }

  private static class ArrayValStandardScheme extends StandardScheme<ArrayVal> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ArrayVal struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // SQL_TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.sqlType = iprot.readI32();
              struct.setSqlTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // ELEMENTS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list8 = iprot.readListBegin();
                struct.elements = new ArrayList<RawVal>(_list8.size);
                RawVal _elem9;
                for (int _i10 = 0; _i10 < _list8.size; ++_i10)
                {
                  _elem9 = new RawVal();
                  _elem9.read(iprot);
                  struct.elements.add(_elem9);
                }
                iprot.readListEnd();
              }
              struct.setElementsIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, ArrayVal struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(SQL_TYPE_FIELD_DESC);
      oprot.writeI32(struct.sqlType);
      oprot.writeFieldEnd();
      if (struct.elements != null) {
        oprot.writeFieldBegin(ELEMENTS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.elements.size()));
          for (RawVal _iter11 : struct.elements)
          {
            _iter11.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ArrayValTupleSchemeFactory implements SchemeFactory {
    public ArrayValTupleScheme getScheme() {
      return new ArrayValTupleScheme();
    }
  }

  private static class ArrayValTupleScheme extends TupleScheme<ArrayVal> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ArrayVal struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetSqlType()) {
        optionals.set(0);
      }
      if (struct.isSetElements()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetSqlType()) {
        oprot.writeI32(struct.sqlType);
      }
      if (struct.isSetElements()) {
        {
          oprot.writeI32(struct.elements.size());
          for (RawVal _iter12 : struct.elements)
          {
            _iter12.write(oprot);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ArrayVal struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.sqlType = iprot.readI32();
        struct.setSqlTypeIsSet(true);
      }
      if (incoming.get(1)) {
        {
          org.apache.thrift.protocol.TList _list13 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.elements = new ArrayList<RawVal>(_list13.size);
          RawVal _elem14;
          for (int _i15 = 0; _i15 < _list13.size; ++_i15)
          {
            _elem14 = new RawVal();
            _elem14.read(iprot);
            struct.elements.add(_elem14);
          }
        }
        struct.setElementsIsSet(true);
      }
    }
  }

}

