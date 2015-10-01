// myCOMShop.cpp : Implementation of WinMain


#include "stdafx.h"
#include "resource.h"
#include "myCOMShop_i.h"



class CmyCOMShopModule : public ATL::CAtlExeModuleT< CmyCOMShopModule >
	{
public :
	DECLARE_LIBID(LIBID_myCOMShopLib)
	DECLARE_REGISTRY_APPID_RESOURCEID(IDR_MYCOMSHOP, "{22633866-5CD0-4BDC-808E-B18A359C6EAD}")
	};

CmyCOMShopModule _AtlModule;



//
extern "C" int WINAPI _tWinMain(HINSTANCE /*hInstance*/, HINSTANCE /*hPrevInstance*/, 
								LPTSTR /*lpCmdLine*/, int nShowCmd)
{
	return _AtlModule.WinMain(nShowCmd);
}

