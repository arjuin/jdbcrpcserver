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
public class RawVal extends org.apache.thrift.TUnion<RawVal, RawVal._Fields> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("RawVal");
  private static final org.apache.thrift.protocol.TField BIGINT_VAL_FIELD_DESC = new org.apache.thrift.protocol.TField("bigint_val", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField INTEGER_VAL_FIELD_DESC = new org.apache.thrift.protocol.TField("integer_val", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField SMALLINT_VAL_FIELD_DESC = new org.apache.thrift.protocol.TField("smallint_val", org.apache.thrift.protocol.TType.I16, (short)3);
  private static final org.apache.thrift.protocol.TField TINYINT_VAL_FIELD_DESC = new org.apache.thrift.protocol.TField("tinyint_val", org.apache.thrift.protocol.TType.BYTE, (short)4);
  private static final org.apache.thrift.protocol.TField DOUBLE_VAL_FIELD_DESC = new org.apache.thrift.protocol.TField("double_val", org.apache.thrift.protocol.TType.DOUBLE, (short)5);
  private static final org.apache.thrift.protocol.TField BOOL_VAL_FIELD_DESC = new org.apache.thrift.protocol.TField("bool_val", org.apache.thrift.protocol.TType.BOOL, (short)6);
  private static final org.apache.thrift.protocol.TField STRING_VAL_FIELD_DESC = new org.apache.thrift.protocol.TField("string_val", org.apache.thrift.protocol.TType.STRING, (short)7);
  private static final org.apache.thrift.protocol.TField MSFROMEPOCH_VAL_FIELD_DESC = new org.apache.thrift.protocol.TField("msfromepoch_val", org.apache.thrift.protocol.TType.BYTE, (short)8);
  private static final org.apache.thrift.protocol.TField BINARY_VAL_FIELD_DESC = new org.apache.thrift.protocol.TField("binary_val", org.apache.thrift.protocol.TType.STRING, (short)9);
  private static final org.apache.thrift.protocol.TField ARRAY_VAL_FIELD_DESC = new org.apache.thrift.protocol.TField("array_val", org.apache.thrift.protocol.TType.STRUCT, (short)10);

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    BIGINT_VAL((short)1, "bigint_val"),
    INTEGER_VAL((short)2, "integer_val"),
    SMALLINT_VAL((short)3, "smallint_val"),
    TINYINT_VAL((short)4, "tinyint_val"),
    DOUBLE_VAL((short)5, "double_val"),
    BOOL_VAL((short)6, "bool_val"),
    STRING_VAL((short)7, "string_val"),
    MSFROMEPOCH_VAL((short)8, "msfromepoch_val"),
    BINARY_VAL((short)9, "binary_val"),
    ARRAY_VAL((short)10, "array_val");

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
        case 1: // BIGINT_VAL
          return BIGINT_VAL;
        case 2: // INTEGER_VAL
          return INTEGER_VAL;
        case 3: // SMALLINT_VAL
          return SMALLINT_VAL;
        case 4: // TINYINT_VAL
          return TINYINT_VAL;
        case 5: // DOUBLE_VAL
          return DOUBLE_VAL;
        case 6: // BOOL_VAL
          return BOOL_VAL;
        case 7: // STRING_VAL
          return STRING_VAL;
        case 8: // MSFROMEPOCH_VAL
          return MSFROMEPOCH_VAL;
        case 9: // BINARY_VAL
          return BINARY_VAL;
        case 10: // ARRAY_VAL
          return ARRAY_VAL;
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

  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.BIGINT_VAL, new org.apache.thrift.meta_data.FieldMetaData("bigint_val", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.INTEGER_VAL, new org.apache.thrift.meta_data.FieldMetaData("integer_val", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.SMALLINT_VAL, new org.apache.thrift.meta_data.FieldMetaData("smallint_val", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I16)));
    tmpMap.put(_Fields.TINYINT_VAL, new org.apache.thrift.meta_data.FieldMetaData("tinyint_val", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BYTE)));
    tmpMap.put(_Fields.DOUBLE_VAL, new org.apache.thrift.meta_data.FieldMetaData("double_val", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    tmpMap.put(_Fields.BOOL_VAL, new org.apache.thrift.meta_data.FieldMetaData("bool_val", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.STRING_VAL, new org.apache.thrift.meta_data.FieldMetaData("string_val", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.MSFROMEPOCH_VAL, new org.apache.thrift.meta_data.FieldMetaData("msfromepoch_val", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BYTE)));
    tmpMap.put(_Fields.BINARY_VAL, new org.apache.thrift.meta_data.FieldMetaData("binary_val", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING        , true)));
    tmpMap.put(_Fields.ARRAY_VAL, new org.apache.thrift.meta_data.FieldMetaData("array_val", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRUCT        , "ArrayVal")));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(RawVal.class, metaDataMap);
  }

  public RawVal() {
    super();
  }

  public RawVal(_Fields setField, Object value) {
    super(setField, value);
  }

  public RawVal(RawVal other) {
    super(other);
  }
  public RawVal deepCopy() {
    return new RawVal(this);
  }

  public static RawVal bigint_val(long value) {
    RawVal x = new RawVal();
    x.setBigint_val(value);
    return x;
  }

  public static RawVal integer_val(int value) {
    RawVal x = new RawVal();
    x.setInteger_val(value);
    return x;
  }

  public static RawVal smallint_val(short value) {
    RawVal x = new RawVal();
    x.setSmallint_val(value);
    return x;
  }

  public static RawVal tinyint_val(byte value) {
    RawVal x = new RawVal();
    x.setTinyint_val(value);
    return x;
  }

  public static RawVal double_val(double value) {
    RawVal x = new RawVal();
    x.setDouble_val(value);
    return x;
  }

  public static RawVal bool_val(boolean value) {
    RawVal x = new RawVal();
    x.setBool_val(value);
    return x;
  }

  public static RawVal string_val(String value) {
    RawVal x = new RawVal();
    x.setString_val(value);
    return x;
  }

  public static RawVal msfromepoch_val(byte value) {
    RawVal x = new RawVal();
    x.setMsfromepoch_val(value);
    return x;
  }

  public static RawVal binary_val(ByteBuffer value) {
    RawVal x = new RawVal();
    x.setBinary_val(value);
    return x;
  }

  public static RawVal binary_val(byte[] value) {
    RawVal x = new RawVal();
    x.setBinary_val(ByteBuffer.wrap(Arrays.copyOf(value, value.length)));
    return x;
  }

  public static RawVal array_val(ArrayVal value) {
    RawVal x = new RawVal();
    x.setArray_val(value);
    return x;
  }


  @Override
  protected void checkType(_Fields setField, Object value) throws ClassCastException {
    switch (setField) {
      case BIGINT_VAL:
        if (value instanceof Long) {
          break;
        }
        throw new ClassCastException("Was expecting value of type Long for field 'bigint_val', but got " + value.getClass().getSimpleName());
      case INTEGER_VAL:
        if (value instanceof Integer) {
          break;
        }
        throw new ClassCastException("Was expecting value of type Integer for field 'integer_val', but got " + value.getClass().getSimpleName());
      case SMALLINT_VAL:
        if (value instanceof Short) {
          break;
        }
        throw new ClassCastException("Was expecting value of type Short for field 'smallint_val', but got " + value.getClass().getSimpleName());
      case TINYINT_VAL:
        if (value instanceof Byte) {
          break;
        }
        throw new ClassCastException("Was expecting value of type Byte for field 'tinyint_val', but got " + value.getClass().getSimpleName());
      case DOUBLE_VAL:
        if (value instanceof Double) {
          break;
        }
        throw new ClassCastException("Was expecting value of type Double for field 'double_val', but got " + value.getClass().getSimpleName());
      case BOOL_VAL:
        if (value instanceof Boolean) {
          break;
        }
        throw new ClassCastException("Was expecting value of type Boolean for field 'bool_val', but got " + value.getClass().getSimpleName());
      case STRING_VAL:
        if (value instanceof String) {
          break;
        }
        throw new ClassCastException("Was expecting value of type String for field 'string_val', but got " + value.getClass().getSimpleName());
      case MSFROMEPOCH_VAL:
        if (value instanceof Byte) {
          break;
        }
        throw new ClassCastException("Was expecting value of type Byte for field 'msfromepoch_val', but got " + value.getClass().getSimpleName());
      case BINARY_VAL:
        if (value instanceof ByteBuffer) {
          break;
        }
        throw new ClassCastException("Was expecting value of type ByteBuffer for field 'binary_val', but got " + value.getClass().getSimpleName());
      case ARRAY_VAL:
        if (value instanceof ArrayVal) {
          break;
        }
        throw new ClassCastException("Was expecting value of type ArrayVal for field 'array_val', but got " + value.getClass().getSimpleName());
      default:
        throw new IllegalArgumentException("Unknown field id " + setField);
    }
  }

  @Override
  protected Object standardSchemeReadValue(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TField field) throws org.apache.thrift.TException {
    _Fields setField = _Fields.findByThriftId(field.id);
    if (setField != null) {
      switch (setField) {
        case BIGINT_VAL:
          if (field.type == BIGINT_VAL_FIELD_DESC.type) {
            Long bigint_val;
            bigint_val = iprot.readI64();
            return bigint_val;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        case INTEGER_VAL:
          if (field.type == INTEGER_VAL_FIELD_DESC.type) {
            Integer integer_val;
            integer_val = iprot.readI32();
            return integer_val;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        case SMALLINT_VAL:
          if (field.type == SMALLINT_VAL_FIELD_DESC.type) {
            Short smallint_val;
            smallint_val = iprot.readI16();
            return smallint_val;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        case TINYINT_VAL:
          if (field.type == TINYINT_VAL_FIELD_DESC.type) {
            Byte tinyint_val;
            tinyint_val = iprot.readByte();
            return tinyint_val;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        case DOUBLE_VAL:
          if (field.type == DOUBLE_VAL_FIELD_DESC.type) {
            Double double_val;
            double_val = iprot.readDouble();
            return double_val;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        case BOOL_VAL:
          if (field.type == BOOL_VAL_FIELD_DESC.type) {
            Boolean bool_val;
            bool_val = iprot.readBool();
            return bool_val;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        case STRING_VAL:
          if (field.type == STRING_VAL_FIELD_DESC.type) {
            String string_val;
            string_val = iprot.readString();
            return string_val;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        case MSFROMEPOCH_VAL:
          if (field.type == MSFROMEPOCH_VAL_FIELD_DESC.type) {
            Byte msfromepoch_val;
            msfromepoch_val = iprot.readByte();
            return msfromepoch_val;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        case BINARY_VAL:
          if (field.type == BINARY_VAL_FIELD_DESC.type) {
            ByteBuffer binary_val;
            binary_val = iprot.readBinary();
            return binary_val;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        case ARRAY_VAL:
          if (field.type == ARRAY_VAL_FIELD_DESC.type) {
            ArrayVal array_val;
            array_val = new ArrayVal();
            array_val.read(iprot);
            return array_val;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        default:
          throw new IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
      }
    } else {
      org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
      return null;
    }
  }

  @Override
  protected void standardSchemeWriteValue(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    switch (setField_) {
      case BIGINT_VAL:
        Long bigint_val = (Long)value_;
        oprot.writeI64(bigint_val);
        return;
      case INTEGER_VAL:
        Integer integer_val = (Integer)value_;
        oprot.writeI32(integer_val);
        return;
      case SMALLINT_VAL:
        Short smallint_val = (Short)value_;
        oprot.writeI16(smallint_val);
        return;
      case TINYINT_VAL:
        Byte tinyint_val = (Byte)value_;
        oprot.writeByte(tinyint_val);
        return;
      case DOUBLE_VAL:
        Double double_val = (Double)value_;
        oprot.writeDouble(double_val);
        return;
      case BOOL_VAL:
        Boolean bool_val = (Boolean)value_;
        oprot.writeBool(bool_val);
        return;
      case STRING_VAL:
        String string_val = (String)value_;
        oprot.writeString(string_val);
        return;
      case MSFROMEPOCH_VAL:
        Byte msfromepoch_val = (Byte)value_;
        oprot.writeByte(msfromepoch_val);
        return;
      case BINARY_VAL:
        ByteBuffer binary_val = (ByteBuffer)value_;
        oprot.writeBinary(binary_val);
        return;
      case ARRAY_VAL:
        ArrayVal array_val = (ArrayVal)value_;
        array_val.write(oprot);
        return;
      default:
        throw new IllegalStateException("Cannot write union with unknown field " + setField_);
    }
  }

  @Override
  protected Object tupleSchemeReadValue(org.apache.thrift.protocol.TProtocol iprot, short fieldID) throws org.apache.thrift.TException {
    _Fields setField = _Fields.findByThriftId(fieldID);
    if (setField != null) {
      switch (setField) {
        case BIGINT_VAL:
          Long bigint_val;
          bigint_val = iprot.readI64();
          return bigint_val;
        case INTEGER_VAL:
          Integer integer_val;
          integer_val = iprot.readI32();
          return integer_val;
        case SMALLINT_VAL:
          Short smallint_val;
          smallint_val = iprot.readI16();
          return smallint_val;
        case TINYINT_VAL:
          Byte tinyint_val;
          tinyint_val = iprot.readByte();
          return tinyint_val;
        case DOUBLE_VAL:
          Double double_val;
          double_val = iprot.readDouble();
          return double_val;
        case BOOL_VAL:
          Boolean bool_val;
          bool_val = iprot.readBool();
          return bool_val;
        case STRING_VAL:
          String string_val;
          string_val = iprot.readString();
          return string_val;
        case MSFROMEPOCH_VAL:
          Byte msfromepoch_val;
          msfromepoch_val = iprot.readByte();
          return msfromepoch_val;
        case BINARY_VAL:
          ByteBuffer binary_val;
          binary_val = iprot.readBinary();
          return binary_val;
        case ARRAY_VAL:
          ArrayVal array_val;
          array_val = new ArrayVal();
          array_val.read(iprot);
          return array_val;
        default:
          throw new IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
      }
    } else {
      throw new TProtocolException("Couldn't find a field with field id " + fieldID);
    }
  }

  @Override
  protected void tupleSchemeWriteValue(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    switch (setField_) {
      case BIGINT_VAL:
        Long bigint_val = (Long)value_;
        oprot.writeI64(bigint_val);
        return;
      case INTEGER_VAL:
        Integer integer_val = (Integer)value_;
        oprot.writeI32(integer_val);
        return;
      case SMALLINT_VAL:
        Short smallint_val = (Short)value_;
        oprot.writeI16(smallint_val);
        return;
      case TINYINT_VAL:
        Byte tinyint_val = (Byte)value_;
        oprot.writeByte(tinyint_val);
        return;
      case DOUBLE_VAL:
        Double double_val = (Double)value_;
        oprot.writeDouble(double_val);
        return;
      case BOOL_VAL:
        Boolean bool_val = (Boolean)value_;
        oprot.writeBool(bool_val);
        return;
      case STRING_VAL:
        String string_val = (String)value_;
        oprot.writeString(string_val);
        return;
      case MSFROMEPOCH_VAL:
        Byte msfromepoch_val = (Byte)value_;
        oprot.writeByte(msfromepoch_val);
        return;
      case BINARY_VAL:
        ByteBuffer binary_val = (ByteBuffer)value_;
        oprot.writeBinary(binary_val);
        return;
      case ARRAY_VAL:
        ArrayVal array_val = (ArrayVal)value_;
        array_val.write(oprot);
        return;
      default:
        throw new IllegalStateException("Cannot write union with unknown field " + setField_);
    }
  }

  @Override
  protected org.apache.thrift.protocol.TField getFieldDesc(_Fields setField) {
    switch (setField) {
      case BIGINT_VAL:
        return BIGINT_VAL_FIELD_DESC;
      case INTEGER_VAL:
        return INTEGER_VAL_FIELD_DESC;
      case SMALLINT_VAL:
        return SMALLINT_VAL_FIELD_DESC;
      case TINYINT_VAL:
        return TINYINT_VAL_FIELD_DESC;
      case DOUBLE_VAL:
        return DOUBLE_VAL_FIELD_DESC;
      case BOOL_VAL:
        return BOOL_VAL_FIELD_DESC;
      case STRING_VAL:
        return STRING_VAL_FIELD_DESC;
      case MSFROMEPOCH_VAL:
        return MSFROMEPOCH_VAL_FIELD_DESC;
      case BINARY_VAL:
        return BINARY_VAL_FIELD_DESC;
      case ARRAY_VAL:
        return ARRAY_VAL_FIELD_DESC;
      default:
        throw new IllegalArgumentException("Unknown field id " + setField);
    }
  }

  @Override
  protected org.apache.thrift.protocol.TStruct getStructDesc() {
    return STRUCT_DESC;
  }

  @Override
  protected _Fields enumForId(short id) {
    return _Fields.findByThriftIdOrThrow(id);
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }


  public long getBigint_val() {
    if (getSetField() == _Fields.BIGINT_VAL) {
      return (Long)getFieldValue();
    } else {
      throw new RuntimeException("Cannot get field 'bigint_val' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setBigint_val(long value) {
    setField_ = _Fields.BIGINT_VAL;
    value_ = value;
  }

  public int getInteger_val() {
    if (getSetField() == _Fields.INTEGER_VAL) {
      return (Integer)getFieldValue();
    } else {
      throw new RuntimeException("Cannot get field 'integer_val' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setInteger_val(int value) {
    setField_ = _Fields.INTEGER_VAL;
    value_ = value;
  }

  public short getSmallint_val() {
    if (getSetField() == _Fields.SMALLINT_VAL) {
      return (Short)getFieldValue();
    } else {
      throw new RuntimeException("Cannot get field 'smallint_val' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setSmallint_val(short value) {
    setField_ = _Fields.SMALLINT_VAL;
    value_ = value;
  }

  public byte getTinyint_val() {
    if (getSetField() == _Fields.TINYINT_VAL) {
      return (Byte)getFieldValue();
    } else {
      throw new RuntimeException("Cannot get field 'tinyint_val' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setTinyint_val(byte value) {
    setField_ = _Fields.TINYINT_VAL;
    value_ = value;
  }

  public double getDouble_val() {
    if (getSetField() == _Fields.DOUBLE_VAL) {
      return (Double)getFieldValue();
    } else {
      throw new RuntimeException("Cannot get field 'double_val' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setDouble_val(double value) {
    setField_ = _Fields.DOUBLE_VAL;
    value_ = value;
  }

  public boolean getBool_val() {
    if (getSetField() == _Fields.BOOL_VAL) {
      return (Boolean)getFieldValue();
    } else {
      throw new RuntimeException("Cannot get field 'bool_val' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setBool_val(boolean value) {
    setField_ = _Fields.BOOL_VAL;
    value_ = value;
  }

  public String getString_val() {
    if (getSetField() == _Fields.STRING_VAL) {
      return (String)getFieldValue();
    } else {
      throw new RuntimeException("Cannot get field 'string_val' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setString_val(String value) {
    if (value == null) throw new NullPointerException();
    setField_ = _Fields.STRING_VAL;
    value_ = value;
  }

  public byte getMsfromepoch_val() {
    if (getSetField() == _Fields.MSFROMEPOCH_VAL) {
      return (Byte)getFieldValue();
    } else {
      throw new RuntimeException("Cannot get field 'msfromepoch_val' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setMsfromepoch_val(byte value) {
    setField_ = _Fields.MSFROMEPOCH_VAL;
    value_ = value;
  }

  public byte[] getBinary_val() {
    setBinary_val(org.apache.thrift.TBaseHelper.rightSize(bufferForBinary_val()));
    ByteBuffer b = bufferForBinary_val();
    return b == null ? null : b.array();
  }

  public ByteBuffer bufferForBinary_val() {
    if (getSetField() == _Fields.BINARY_VAL) {
      return org.apache.thrift.TBaseHelper.copyBinary((ByteBuffer)getFieldValue());
    } else {
      throw new RuntimeException("Cannot get field 'binary_val' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setBinary_val(byte[] value) {
    setBinary_val(ByteBuffer.wrap(Arrays.copyOf(value, value.length)));
  }

  public void setBinary_val(ByteBuffer value) {
    if (value == null) throw new NullPointerException();
    setField_ = _Fields.BINARY_VAL;
    value_ = value;
  }

  public ArrayVal getArray_val() {
    if (getSetField() == _Fields.ARRAY_VAL) {
      return (ArrayVal)getFieldValue();
    } else {
      throw new RuntimeException("Cannot get field 'array_val' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setArray_val(ArrayVal value) {
    if (value == null) throw new NullPointerException();
    setField_ = _Fields.ARRAY_VAL;
    value_ = value;
  }

  public boolean isSetBigint_val() {
    return setField_ == _Fields.BIGINT_VAL;
  }


  public boolean isSetInteger_val() {
    return setField_ == _Fields.INTEGER_VAL;
  }


  public boolean isSetSmallint_val() {
    return setField_ == _Fields.SMALLINT_VAL;
  }


  public boolean isSetTinyint_val() {
    return setField_ == _Fields.TINYINT_VAL;
  }


  public boolean isSetDouble_val() {
    return setField_ == _Fields.DOUBLE_VAL;
  }


  public boolean isSetBool_val() {
    return setField_ == _Fields.BOOL_VAL;
  }


  public boolean isSetString_val() {
    return setField_ == _Fields.STRING_VAL;
  }


  public boolean isSetMsfromepoch_val() {
    return setField_ == _Fields.MSFROMEPOCH_VAL;
  }


  public boolean isSetBinary_val() {
    return setField_ == _Fields.BINARY_VAL;
  }


  public boolean isSetArray_val() {
    return setField_ == _Fields.ARRAY_VAL;
  }


  public boolean equals(Object other) {
    if (other instanceof RawVal) {
      return equals((RawVal)other);
    } else {
      return false;
    }
  }

  public boolean equals(RawVal other) {
    return other != null && getSetField() == other.getSetField() && getFieldValue().equals(other.getFieldValue());
  }

  @Override
  public int compareTo(RawVal other) {
    int lastComparison = org.apache.thrift.TBaseHelper.compareTo(getSetField(), other.getSetField());
    if (lastComparison == 0) {
      return org.apache.thrift.TBaseHelper.compareTo(getFieldValue(), other.getFieldValue());
    }
    return lastComparison;
  }


  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();
    list.add(this.getClass().getName());
    org.apache.thrift.TFieldIdEnum setField = getSetField();
    if (setField != null) {
      list.add(setField.getThriftFieldId());
      Object value = getFieldValue();
      if (value instanceof org.apache.thrift.TEnum) {
        list.add(((org.apache.thrift.TEnum)getFieldValue()).getValue());
      } else {
        list.add(value);
      }
    }
    return list.hashCode();
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
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }


}
