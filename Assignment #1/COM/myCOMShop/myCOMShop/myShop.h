// myShop.h : Declaration of the CmyShop

#pragma once
#include "resource.h"       // main symbols



#include "myCOMShop_i.h"


//Added code
#include "ShopTable.h"


#if defined(_WIN32_WCE) && !defined(_CE_DCOM) && !defined(_CE_ALLOW_SINGLE_THREADED_OBJECTS_IN_MTA)
#error "Single-threaded COM objects are not properly supported on Windows CE platform, such as the Windows Mobile platforms that do not include full DCOM support. Define _CE_ALLOW_SINGLE_THREADED_OBJECTS_IN_MTA to force ATL to support creating single-thread COM object's and allow use of it's single-threaded COM object implementations. The threading model in your rgs file was set to 'Free' as that is the only threading model supported in non DCOM Windows CE platforms."
#endif

using namespace ATL;


// CmyShop

class ATL_NO_VTABLE CmyShop :
	public CComObjectRootEx<CComSingleThreadModel>,
	public CComCoClass<CmyShop, &CLSID_myShop>,
	public IDispatchImpl<ImyShop, &IID_ImyShop, &LIBID_myCOMShopLib, /*wMajor =*/ 1, /*wMinor =*/ 0>
{

//Added code
private:
	CShopTable m_db;
	int m_currentIndex;
	bool windToIndex(int index);


public:
	CmyShop()
	{
	}

DECLARE_REGISTRY_RESOURCEID(IDR_MYSHOP)


BEGIN_COM_MAP(CmyShop)
	COM_INTERFACE_ENTRY(ImyShop)
	COM_INTERFACE_ENTRY(IDispatch)
END_COM_MAP()



	DECLARE_PROTECT_FINAL_CONSTRUCT()

	HRESULT FinalConstruct()
	{
		//Added code
		HRESULT hr = m_db.OpenAll();
		m_currentIndex = -1;
		return hr;
	}

	void FinalRelease()
	{
		//Added code
		m_db.CloseAll();
	}

public:



	STDMETHOD(get_Count)(LONG* pVal);
	STDMETHOD(getNameByIndex)(LONG index, BSTR* name);
	STDMETHOD(getCostByIndex)(LONG index, LONG* pVal);
	STDMETHOD(getCountByIndex)(LONG index, LONG* pVal);
	STDMETHOD(buy)(LONG index);
};

OBJECT_ENTRY_AUTO(__uuidof(myShop), CmyShop)
