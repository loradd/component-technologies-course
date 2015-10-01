// myShop.cpp : Implementation of CmyShop

#include "stdafx.h"
#include "myShop.h"


// CmyShop


STDMETHODIMP CmyShop::get_Count(LONG* pVal)
{
	//Added code
	m_currentIndex = -1;
	m_db.MoveFirst();
	int count = 0;
	while (m_db.MoveNext() == S_OK) count++;
	*pVal = count + 1;
	return S_OK;
}


STDMETHODIMP CmyShop::getNameByIndex(LONG index, BSTR* name)
{
	//Added code
	if (!windToIndex(index)) return E_FAIL;
	CComBSTR nameVal = m_db.m_ProductName;
	return nameVal.CopyTo(name);
}


STDMETHODIMP CmyShop::getCostByIndex(LONG index, LONG* pVal)
{
	//Added code
	if (!windToIndex(index)) return E_FAIL;
	*pVal = m_db.m_Price.Lo;
	return S_OK;
}


STDMETHODIMP CmyShop::getCountByIndex(LONG index, LONG* pVal)
{
	//Added code
	if (!windToIndex(index)) return E_FAIL;
	*pVal = m_db.m_ItemsInStock;
	return S_OK;
}


//Buy one (piece) item a time
STDMETHODIMP CmyShop::buy(LONG index)
{
	//Added code
	if (!windToIndex(index)) return E_FAIL;
	if (m_db.m_ItemsInStock <= 0) return E_FAIL;
	m_db.m_ItemsInStock--;
	return m_db.SetData();
}


// This private help function winds the recordset to the given index.
// To increase performance, we cache the current index.
// Another improvement would be to move from currentIndex to index
// without MoveFirst.
// Of course, best is to select the row with Some SQL statement but
// that is not the goal of this exercise.

bool CmyShop::windToIndex(int index)
{
	if (m_currentIndex == index) //Nothing to do
		return true;
	if (index < 0)
		return false; //Index too small

	int tmpIndex = index;
	m_db.MoveFirst();
	while (tmpIndex-- > 0 && m_db.MoveNext() == S_OK) { /*nothing*/ }

	if (tmpIndex != -1) //Index too big. Reset cached index
	{
		m_currentIndex = -1;
		return false;
	}
	m_currentIndex = index;
	return true;
}
