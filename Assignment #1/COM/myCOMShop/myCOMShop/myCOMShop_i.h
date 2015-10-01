

/* this ALWAYS GENERATED file contains the definitions for the interfaces */


 /* File created by MIDL compiler version 7.00.0555 */
/* at Thu Oct 01 16:03:41 2015
 */
/* Compiler settings for myCOMShop.idl:
    Oicf, W1, Zp8, env=Win32 (32b run), target_arch=X86 7.00.0555 
    protocol : dce , ms_ext, c_ext, robust
    error checks: allocation ref bounds_check enum stub_data 
    VC __declspec() decoration level: 
         __declspec(uuid()), __declspec(selectany), __declspec(novtable)
         DECLSPEC_UUID(), MIDL_INTERFACE()
*/
/* @@MIDL_FILE_HEADING(  ) */

#pragma warning( disable: 4049 )  /* more than 64k source lines */


/* verify that the <rpcndr.h> version is high enough to compile this file*/
#ifndef __REQUIRED_RPCNDR_H_VERSION__
#define __REQUIRED_RPCNDR_H_VERSION__ 475
#endif

#include "rpc.h"
#include "rpcndr.h"

#ifndef __RPCNDR_H_VERSION__
#error this stub requires an updated version of <rpcndr.h>
#endif // __RPCNDR_H_VERSION__

#ifndef COM_NO_WINDOWS_H
#include "windows.h"
#include "ole2.h"
#endif /*COM_NO_WINDOWS_H*/

#ifndef __myCOMShop_i_h__
#define __myCOMShop_i_h__

#if defined(_MSC_VER) && (_MSC_VER >= 1020)
#pragma once
#endif

/* Forward Declarations */ 

#ifndef __ImyShop_FWD_DEFINED__
#define __ImyShop_FWD_DEFINED__
typedef interface ImyShop ImyShop;
#endif 	/* __ImyShop_FWD_DEFINED__ */


#ifndef __myShop_FWD_DEFINED__
#define __myShop_FWD_DEFINED__

#ifdef __cplusplus
typedef class myShop myShop;
#else
typedef struct myShop myShop;
#endif /* __cplusplus */

#endif 	/* __myShop_FWD_DEFINED__ */


/* header files for imported files */
#include "oaidl.h"
#include "ocidl.h"
#include "shobjidl.h"

#ifdef __cplusplus
extern "C"{
#endif 


#ifndef __ImyShop_INTERFACE_DEFINED__
#define __ImyShop_INTERFACE_DEFINED__

/* interface ImyShop */
/* [unique][nonextensible][dual][uuid][object] */ 


EXTERN_C const IID IID_ImyShop;

#if defined(__cplusplus) && !defined(CINTERFACE)
    
    MIDL_INTERFACE("22243DEA-865D-4838-A56C-C9032337E97F")
    ImyShop : public IDispatch
    {
    public:
        virtual /* [id][propget] */ HRESULT STDMETHODCALLTYPE get_Count( 
            /* [retval][out] */ LONG *pVal) = 0;
        
        virtual /* [id] */ HRESULT STDMETHODCALLTYPE getNameByIndex( 
            /* [in] */ LONG index,
            /* [retval][out] */ BSTR *name) = 0;
        
        virtual /* [id] */ HRESULT STDMETHODCALLTYPE getCostByIndex( 
            /* [in] */ LONG index,
            /* [retval][out] */ LONG *pVal) = 0;
        
        virtual /* [id] */ HRESULT STDMETHODCALLTYPE getCountByIndex( 
            /* [in] */ LONG index,
            /* [retval][out] */ LONG *pVal) = 0;
        
        virtual /* [id] */ HRESULT STDMETHODCALLTYPE buy( 
            /* [in] */ LONG index) = 0;
        
    };
    
#else 	/* C style interface */

    typedef struct ImyShopVtbl
    {
        BEGIN_INTERFACE
        
        HRESULT ( STDMETHODCALLTYPE *QueryInterface )( 
            ImyShop * This,
            /* [in] */ REFIID riid,
            /* [annotation][iid_is][out] */ 
            __RPC__deref_out  void **ppvObject);
        
        ULONG ( STDMETHODCALLTYPE *AddRef )( 
            ImyShop * This);
        
        ULONG ( STDMETHODCALLTYPE *Release )( 
            ImyShop * This);
        
        HRESULT ( STDMETHODCALLTYPE *GetTypeInfoCount )( 
            ImyShop * This,
            /* [out] */ UINT *pctinfo);
        
        HRESULT ( STDMETHODCALLTYPE *GetTypeInfo )( 
            ImyShop * This,
            /* [in] */ UINT iTInfo,
            /* [in] */ LCID lcid,
            /* [out] */ ITypeInfo **ppTInfo);
        
        HRESULT ( STDMETHODCALLTYPE *GetIDsOfNames )( 
            ImyShop * This,
            /* [in] */ REFIID riid,
            /* [size_is][in] */ LPOLESTR *rgszNames,
            /* [range][in] */ UINT cNames,
            /* [in] */ LCID lcid,
            /* [size_is][out] */ DISPID *rgDispId);
        
        /* [local] */ HRESULT ( STDMETHODCALLTYPE *Invoke )( 
            ImyShop * This,
            /* [in] */ DISPID dispIdMember,
            /* [in] */ REFIID riid,
            /* [in] */ LCID lcid,
            /* [in] */ WORD wFlags,
            /* [out][in] */ DISPPARAMS *pDispParams,
            /* [out] */ VARIANT *pVarResult,
            /* [out] */ EXCEPINFO *pExcepInfo,
            /* [out] */ UINT *puArgErr);
        
        /* [id][propget] */ HRESULT ( STDMETHODCALLTYPE *get_Count )( 
            ImyShop * This,
            /* [retval][out] */ LONG *pVal);
        
        /* [id] */ HRESULT ( STDMETHODCALLTYPE *getNameByIndex )( 
            ImyShop * This,
            /* [in] */ LONG index,
            /* [retval][out] */ BSTR *name);
        
        /* [id] */ HRESULT ( STDMETHODCALLTYPE *getCostByIndex )( 
            ImyShop * This,
            /* [in] */ LONG index,
            /* [retval][out] */ LONG *pVal);
        
        /* [id] */ HRESULT ( STDMETHODCALLTYPE *getCountByIndex )( 
            ImyShop * This,
            /* [in] */ LONG index,
            /* [retval][out] */ LONG *pVal);
        
        /* [id] */ HRESULT ( STDMETHODCALLTYPE *buy )( 
            ImyShop * This,
            /* [in] */ LONG index);
        
        END_INTERFACE
    } ImyShopVtbl;

    interface ImyShop
    {
        CONST_VTBL struct ImyShopVtbl *lpVtbl;
    };

    

#ifdef COBJMACROS


#define ImyShop_QueryInterface(This,riid,ppvObject)	\
    ( (This)->lpVtbl -> QueryInterface(This,riid,ppvObject) ) 

#define ImyShop_AddRef(This)	\
    ( (This)->lpVtbl -> AddRef(This) ) 

#define ImyShop_Release(This)	\
    ( (This)->lpVtbl -> Release(This) ) 


#define ImyShop_GetTypeInfoCount(This,pctinfo)	\
    ( (This)->lpVtbl -> GetTypeInfoCount(This,pctinfo) ) 

#define ImyShop_GetTypeInfo(This,iTInfo,lcid,ppTInfo)	\
    ( (This)->lpVtbl -> GetTypeInfo(This,iTInfo,lcid,ppTInfo) ) 

#define ImyShop_GetIDsOfNames(This,riid,rgszNames,cNames,lcid,rgDispId)	\
    ( (This)->lpVtbl -> GetIDsOfNames(This,riid,rgszNames,cNames,lcid,rgDispId) ) 

#define ImyShop_Invoke(This,dispIdMember,riid,lcid,wFlags,pDispParams,pVarResult,pExcepInfo,puArgErr)	\
    ( (This)->lpVtbl -> Invoke(This,dispIdMember,riid,lcid,wFlags,pDispParams,pVarResult,pExcepInfo,puArgErr) ) 


#define ImyShop_get_Count(This,pVal)	\
    ( (This)->lpVtbl -> get_Count(This,pVal) ) 

#define ImyShop_getNameByIndex(This,index,name)	\
    ( (This)->lpVtbl -> getNameByIndex(This,index,name) ) 

#define ImyShop_getCostByIndex(This,index,pVal)	\
    ( (This)->lpVtbl -> getCostByIndex(This,index,pVal) ) 

#define ImyShop_getCountByIndex(This,index,pVal)	\
    ( (This)->lpVtbl -> getCountByIndex(This,index,pVal) ) 

#define ImyShop_buy(This,index)	\
    ( (This)->lpVtbl -> buy(This,index) ) 

#endif /* COBJMACROS */


#endif 	/* C style interface */




#endif 	/* __ImyShop_INTERFACE_DEFINED__ */



#ifndef __myCOMShopLib_LIBRARY_DEFINED__
#define __myCOMShopLib_LIBRARY_DEFINED__

/* library myCOMShopLib */
/* [version][uuid] */ 


EXTERN_C const IID LIBID_myCOMShopLib;

EXTERN_C const CLSID CLSID_myShop;

#ifdef __cplusplus

class DECLSPEC_UUID("F9E74E42-71C4-4D71-9158-F403A787B9BA")
myShop;
#endif
#endif /* __myCOMShopLib_LIBRARY_DEFINED__ */

/* Additional Prototypes for ALL interfaces */

unsigned long             __RPC_USER  BSTR_UserSize(     unsigned long *, unsigned long            , BSTR * ); 
unsigned char * __RPC_USER  BSTR_UserMarshal(  unsigned long *, unsigned char *, BSTR * ); 
unsigned char * __RPC_USER  BSTR_UserUnmarshal(unsigned long *, unsigned char *, BSTR * ); 
void                      __RPC_USER  BSTR_UserFree(     unsigned long *, BSTR * ); 

/* end of Additional Prototypes */

#ifdef __cplusplus
}
#endif

#endif


