// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Userinfo.proto

package net.netty.plugins.pojo;

public final class User {
  private User() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface UserInfoOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required string name = 1;
    /**
     * <code>required string name = 1;</code>
     */
    boolean hasName();
    /**
     * <code>required string name = 1;</code>
     */
    java.lang.String getName();
    /**
     * <code>required string name = 1;</code>
     */
    com.google.protobuf.ByteString
        getNameBytes();

    // required int32 age = 2;
    /**
     * <code>required int32 age = 2;</code>
     */
    boolean hasAge();
    /**
     * <code>required int32 age = 2;</code>
     */
    int getAge();

    // required string info = 3;
    /**
     * <code>required string info = 3;</code>
     */
    boolean hasInfo();
    /**
     * <code>required string info = 3;</code>
     */
    java.lang.String getInfo();
    /**
     * <code>required string info = 3;</code>
     */
    com.google.protobuf.ByteString
        getInfoBytes();
  }
  /**
   * Protobuf type {@code netty.UserInfo}
   */
  public static final class UserInfo extends
      com.google.protobuf.GeneratedMessage
      implements UserInfoOrBuilder {
    // Use UserInfo.newBuilder() to construct.
    private UserInfo(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private UserInfo(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final UserInfo defaultInstance;
    public static UserInfo getDefaultInstance() {
      return defaultInstance;
    }

    public UserInfo getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private UserInfo(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              bitField0_ |= 0x00000001;
              name_ = input.readBytes();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              age_ = input.readInt32();
              break;
            }
            case 26: {
              bitField0_ |= 0x00000004;
              info_ = input.readBytes();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return net.netty.plugins.pojo.User.internal_static_netty_UserInfo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return net.netty.plugins.pojo.User.internal_static_netty_UserInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              net.netty.plugins.pojo.User.UserInfo.class, net.netty.plugins.pojo.User.UserInfo.Builder.class);
    }

    public static com.google.protobuf.Parser<UserInfo> PARSER =
        new com.google.protobuf.AbstractParser<UserInfo>() {
      public UserInfo parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new UserInfo(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<UserInfo> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // required string name = 1;
    public static final int NAME_FIELD_NUMBER = 1;
    private java.lang.Object name_;
    /**
     * <code>required string name = 1;</code>
     */
    public boolean hasName() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required string name = 1;</code>
     */
    public java.lang.String getName() {
      java.lang.Object ref = name_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          name_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string name = 1;</code>
     */
    public com.google.protobuf.ByteString
        getNameBytes() {
      java.lang.Object ref = name_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    // required int32 age = 2;
    public static final int AGE_FIELD_NUMBER = 2;
    private int age_;
    /**
     * <code>required int32 age = 2;</code>
     */
    public boolean hasAge() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required int32 age = 2;</code>
     */
    public int getAge() {
      return age_;
    }

    // required string info = 3;
    public static final int INFO_FIELD_NUMBER = 3;
    private java.lang.Object info_;
    /**
     * <code>required string info = 3;</code>
     */
    public boolean hasInfo() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required string info = 3;</code>
     */
    public java.lang.String getInfo() {
      java.lang.Object ref = info_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          info_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string info = 3;</code>
     */
    public com.google.protobuf.ByteString
        getInfoBytes() {
      java.lang.Object ref = info_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        info_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private void initFields() {
      name_ = "";
      age_ = 0;
      info_ = "";
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasName()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasAge()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasInfo()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeBytes(1, getNameBytes());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(2, age_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeBytes(3, getInfoBytes());
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(1, getNameBytes());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, age_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(3, getInfoBytes());
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static net.netty.plugins.pojo.User.UserInfo parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static net.netty.plugins.pojo.User.UserInfo parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static net.netty.plugins.pojo.User.UserInfo parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static net.netty.plugins.pojo.User.UserInfo parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static net.netty.plugins.pojo.User.UserInfo parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static net.netty.plugins.pojo.User.UserInfo parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static net.netty.plugins.pojo.User.UserInfo parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static net.netty.plugins.pojo.User.UserInfo parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static net.netty.plugins.pojo.User.UserInfo parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static net.netty.plugins.pojo.User.UserInfo parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(net.netty.plugins.pojo.User.UserInfo prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code netty.UserInfo}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements net.netty.plugins.pojo.User.UserInfoOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return net.netty.plugins.pojo.User.internal_static_netty_UserInfo_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return net.netty.plugins.pojo.User.internal_static_netty_UserInfo_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                net.netty.plugins.pojo.User.UserInfo.class, net.netty.plugins.pojo.User.UserInfo.Builder.class);
      }

      // Construct using net.netty.plugins.pojo.User.UserInfo.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        name_ = "";
        bitField0_ = (bitField0_ & ~0x00000001);
        age_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        info_ = "";
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return net.netty.plugins.pojo.User.internal_static_netty_UserInfo_descriptor;
      }

      public net.netty.plugins.pojo.User.UserInfo getDefaultInstanceForType() {
        return net.netty.plugins.pojo.User.UserInfo.getDefaultInstance();
      }

      public net.netty.plugins.pojo.User.UserInfo build() {
        net.netty.plugins.pojo.User.UserInfo result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public net.netty.plugins.pojo.User.UserInfo buildPartial() {
        net.netty.plugins.pojo.User.UserInfo result = new net.netty.plugins.pojo.User.UserInfo(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.name_ = name_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.age_ = age_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.info_ = info_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof net.netty.plugins.pojo.User.UserInfo) {
          return mergeFrom((net.netty.plugins.pojo.User.UserInfo)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(net.netty.plugins.pojo.User.UserInfo other) {
        if (other == net.netty.plugins.pojo.User.UserInfo.getDefaultInstance()) return this;
        if (other.hasName()) {
          bitField0_ |= 0x00000001;
          name_ = other.name_;
          onChanged();
        }
        if (other.hasAge()) {
          setAge(other.getAge());
        }
        if (other.hasInfo()) {
          bitField0_ |= 0x00000004;
          info_ = other.info_;
          onChanged();
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasName()) {
          
          return false;
        }
        if (!hasAge()) {
          
          return false;
        }
        if (!hasInfo()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        net.netty.plugins.pojo.User.UserInfo parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (net.netty.plugins.pojo.User.UserInfo) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // required string name = 1;
      private java.lang.Object name_ = "";
      /**
       * <code>required string name = 1;</code>
       */
      public boolean hasName() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required string name = 1;</code>
       */
      public java.lang.String getName() {
        java.lang.Object ref = name_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          name_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string name = 1;</code>
       */
      public com.google.protobuf.ByteString
          getNameBytes() {
        java.lang.Object ref = name_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          name_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string name = 1;</code>
       */
      public Builder setName(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        name_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string name = 1;</code>
       */
      public Builder clearName() {
        bitField0_ = (bitField0_ & ~0x00000001);
        name_ = getDefaultInstance().getName();
        onChanged();
        return this;
      }
      /**
       * <code>required string name = 1;</code>
       */
      public Builder setNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        name_ = value;
        onChanged();
        return this;
      }

      // required int32 age = 2;
      private int age_ ;
      /**
       * <code>required int32 age = 2;</code>
       */
      public boolean hasAge() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required int32 age = 2;</code>
       */
      public int getAge() {
        return age_;
      }
      /**
       * <code>required int32 age = 2;</code>
       */
      public Builder setAge(int value) {
        bitField0_ |= 0x00000002;
        age_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 age = 2;</code>
       */
      public Builder clearAge() {
        bitField0_ = (bitField0_ & ~0x00000002);
        age_ = 0;
        onChanged();
        return this;
      }

      // required string info = 3;
      private java.lang.Object info_ = "";
      /**
       * <code>required string info = 3;</code>
       */
      public boolean hasInfo() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>required string info = 3;</code>
       */
      public java.lang.String getInfo() {
        java.lang.Object ref = info_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          info_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string info = 3;</code>
       */
      public com.google.protobuf.ByteString
          getInfoBytes() {
        java.lang.Object ref = info_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          info_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string info = 3;</code>
       */
      public Builder setInfo(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        info_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string info = 3;</code>
       */
      public Builder clearInfo() {
        bitField0_ = (bitField0_ & ~0x00000004);
        info_ = getDefaultInstance().getInfo();
        onChanged();
        return this;
      }
      /**
       * <code>required string info = 3;</code>
       */
      public Builder setInfoBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        info_ = value;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:netty.UserInfo)
    }

    static {
      defaultInstance = new UserInfo(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:netty.UserInfo)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_netty_UserInfo_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_netty_UserInfo_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\016Userinfo.proto\022\005netty\"3\n\010UserInfo\022\014\n\004n" +
      "ame\030\001 \002(\t\022\013\n\003age\030\002 \002(\005\022\014\n\004info\030\003 \002(\tB\036\n\026" +
      "net.netty.plugins.pojoB\004User"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_netty_UserInfo_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_netty_UserInfo_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_netty_UserInfo_descriptor,
              new java.lang.String[] { "Name", "Age", "Info", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}