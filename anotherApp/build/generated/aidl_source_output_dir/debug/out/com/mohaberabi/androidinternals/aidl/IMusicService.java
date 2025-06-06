/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Using: /Users/mohaberabi/Library/Android/sdk/build-tools/35.0.0/aidl -p/Users/mohaberabi/Library/Android/sdk/platforms/android-35/framework.aidl -o/Users/mohaberabi/development/projects/AndroidInternals/anotherApp/build/generated/aidl_source_output_dir/debug/out -I/Users/mohaberabi/development/projects/AndroidInternals/anotherApp/src/main/aidl -I/Users/mohaberabi/development/projects/AndroidInternals/anotherApp/src/debug/aidl -I/Users/mohaberabi/.gradle/caches/8.10.2/transforms/5691e77c1972fde26de3116018e5a7cc/transformed/core-1.16.0/aidl -I/Users/mohaberabi/.gradle/caches/8.10.2/transforms/d340a55c8dd93c92972bd0b1acc55093/transformed/versionedparcelable-1.1.1/aidl -d/var/folders/gx/qrb5f3550fd4q2ldmtvdt2tm0000gn/T/aidl3629851219718706449.d /Users/mohaberabi/development/projects/AndroidInternals/anotherApp/src/main/aidl/com/mohaberabi/androidinternals/aidl/IMusicService.aidl
 */
package com.mohaberabi.androidinternals.aidl;
public interface IMusicService extends android.os.IInterface
{
  /** Default implementation for IMusicService. */
  public static class Default implements com.mohaberabi.androidinternals.aidl.IMusicService
  {
    @Override public void next() throws android.os.RemoteException
    {
    }
    @Override public void previous() throws android.os.RemoteException
    {
    }
    @Override public java.lang.String getCurrentSongName() throws android.os.RemoteException
    {
      return null;
    }
    @Override public void registerCallBack(com.mohaberabi.androidinternals.aidl.ISongNameChangedCallback callback) throws android.os.RemoteException
    {
    }
    @Override public void unregisterCallBack(com.mohaberabi.androidinternals.aidl.ISongNameChangedCallback callback) throws android.os.RemoteException
    {
    }
    @Override
    public android.os.IBinder asBinder() {
      return null;
    }
  }
  /** Local-side IPC implementation stub class. */
  public static abstract class Stub extends android.os.Binder implements com.mohaberabi.androidinternals.aidl.IMusicService
  {
    /** Construct the stub at attach it to the interface. */
    @SuppressWarnings("this-escape")
    public Stub()
    {
      this.attachInterface(this, DESCRIPTOR);
    }
    /**
     * Cast an IBinder object into an com.mohaberabi.androidinternals.aidl.IMusicService interface,
     * generating a proxy if needed.
     */
    public static com.mohaberabi.androidinternals.aidl.IMusicService asInterface(android.os.IBinder obj)
    {
      if ((obj==null)) {
        return null;
      }
      android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
      if (((iin!=null)&&(iin instanceof com.mohaberabi.androidinternals.aidl.IMusicService))) {
        return ((com.mohaberabi.androidinternals.aidl.IMusicService)iin);
      }
      return new com.mohaberabi.androidinternals.aidl.IMusicService.Stub.Proxy(obj);
    }
    @Override public android.os.IBinder asBinder()
    {
      return this;
    }
    @Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
    {
      java.lang.String descriptor = DESCRIPTOR;
      if (code >= android.os.IBinder.FIRST_CALL_TRANSACTION && code <= android.os.IBinder.LAST_CALL_TRANSACTION) {
        data.enforceInterface(descriptor);
      }
      if (code == INTERFACE_TRANSACTION) {
        reply.writeString(descriptor);
        return true;
      }
      switch (code)
      {
        case TRANSACTION_next:
        {
          this.next();
          reply.writeNoException();
          break;
        }
        case TRANSACTION_previous:
        {
          this.previous();
          reply.writeNoException();
          break;
        }
        case TRANSACTION_getCurrentSongName:
        {
          java.lang.String _result = this.getCurrentSongName();
          reply.writeNoException();
          reply.writeString(_result);
          break;
        }
        case TRANSACTION_registerCallBack:
        {
          com.mohaberabi.androidinternals.aidl.ISongNameChangedCallback _arg0;
          _arg0 = com.mohaberabi.androidinternals.aidl.ISongNameChangedCallback.Stub.asInterface(data.readStrongBinder());
          this.registerCallBack(_arg0);
          reply.writeNoException();
          break;
        }
        case TRANSACTION_unregisterCallBack:
        {
          com.mohaberabi.androidinternals.aidl.ISongNameChangedCallback _arg0;
          _arg0 = com.mohaberabi.androidinternals.aidl.ISongNameChangedCallback.Stub.asInterface(data.readStrongBinder());
          this.unregisterCallBack(_arg0);
          reply.writeNoException();
          break;
        }
        default:
        {
          return super.onTransact(code, data, reply, flags);
        }
      }
      return true;
    }
    private static class Proxy implements com.mohaberabi.androidinternals.aidl.IMusicService
    {
      private android.os.IBinder mRemote;
      Proxy(android.os.IBinder remote)
      {
        mRemote = remote;
      }
      @Override public android.os.IBinder asBinder()
      {
        return mRemote;
      }
      public java.lang.String getInterfaceDescriptor()
      {
        return DESCRIPTOR;
      }
      @Override public void next() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_next, _data, _reply, 0);
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      @Override public void previous() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_previous, _data, _reply, 0);
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      @Override public java.lang.String getCurrentSongName() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        java.lang.String _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getCurrentSongName, _data, _reply, 0);
          _reply.readException();
          _result = _reply.readString();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      @Override public void registerCallBack(com.mohaberabi.androidinternals.aidl.ISongNameChangedCallback callback) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeStrongInterface(callback);
          boolean _status = mRemote.transact(Stub.TRANSACTION_registerCallBack, _data, _reply, 0);
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      @Override public void unregisterCallBack(com.mohaberabi.androidinternals.aidl.ISongNameChangedCallback callback) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeStrongInterface(callback);
          boolean _status = mRemote.transact(Stub.TRANSACTION_unregisterCallBack, _data, _reply, 0);
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
    }
    static final int TRANSACTION_next = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    static final int TRANSACTION_previous = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
    static final int TRANSACTION_getCurrentSongName = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
    static final int TRANSACTION_registerCallBack = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
    static final int TRANSACTION_unregisterCallBack = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
  }
  /** @hide */
  public static final java.lang.String DESCRIPTOR = "com.mohaberabi.androidinternals.aidl.IMusicService";
  public void next() throws android.os.RemoteException;
  public void previous() throws android.os.RemoteException;
  public java.lang.String getCurrentSongName() throws android.os.RemoteException;
  public void registerCallBack(com.mohaberabi.androidinternals.aidl.ISongNameChangedCallback callback) throws android.os.RemoteException;
  public void unregisterCallBack(com.mohaberabi.androidinternals.aidl.ISongNameChangedCallback callback) throws android.os.RemoteException;
}
