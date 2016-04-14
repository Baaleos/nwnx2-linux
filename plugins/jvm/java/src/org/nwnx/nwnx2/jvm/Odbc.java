package org.nwnx.nwnx2.jvm;

public class Odbc {

	/**
	 * If iExpiration is unused- set to 0
	 * If sTable is unused, set to pwdata
	 * @param oObject
	 * @param sVarName
	 * @param iValue
	 * @param iExpiration
	 * @param sTable
	 */
	public static void setPersistentInt(NWObject oObject, String sVarName, int iValue, int iExpiration, String sTable)
	{
		if(sTable == null){
			sTable = "pwdata";
		}
		setPersistentString(oObject, sVarName, String.valueOf(iValue), iExpiration, sTable);
	}
	
	public static final int SQL_ERROR = 0;
	public static final int SQL_SUCCESS = 1;
	public static int SQLFetch()
	{
	    String sRow;
	    NWObject oModule = NWObject.MODULE;

	    NWScript.setLocalString(oModule, "NWNX!ODBC!FETCH", NWScript.getLocalString(oModule, "NWNX!ODBC!SPACER"));
	    sRow = NWScript.getLocalString(oModule, "NWNX!ODBC!FETCH");
	    if (sRow.length() > 0)
	    {
	    	NWScript.setLocalString(oModule, "NWNX_ODBC_CurrentRow", sRow);
	        return SQL_SUCCESS;
	    }
	    else
	    {
	    	NWScript.setLocalString(oModule, "NWNX_ODBC_CurrentRow", "");
	        return SQL_ERROR;
	    }
	}
	
	public static String SQLEncodeSpecialChars(String sString)
	{
	    return sString.replace("'","~");
	}
	
	public static void SQLExecDirect(String sSQL)
	{
	    NWScript.setLocalString(NWObject.MODULE, "NWNX!ODBC!EXEC", sSQL);
	}
	public static void setPersistentString(NWObject oObject, String sVarName, String sValue, int iExpiration, String sTable)
		{
			String sPlayer;
			String sTag;
			
			if (NWScript.getIsPC(oObject))
			{
				sPlayer = SQLEncodeSpecialChars(NWScript.getPCPlayerName(oObject));
				sTag = SQLEncodeSpecialChars(NWScript.getName(oObject,false));
			}
			else
			{
				sPlayer = "~";
				sTag = NWScript.getTag(oObject);
			}
			
			sVarName = SQLEncodeSpecialChars(sVarName);
			sValue = SQLEncodeSpecialChars(sValue);
			
			String sSQL = "SELECT player FROM " + sTable + " WHERE player='" + sPlayer +
			"' AND tag='" + sTag + "' AND name='" + sVarName + "'";
			SQLExecDirect(sSQL);
			
			if (SQLFetch() == SQL_SUCCESS)
			{
				// row exists
				sSQL = "UPDATE " + sTable + " SET val='" + sValue +
				"',expire=" + String.valueOf(iExpiration) + " WHERE player='" + sPlayer +
				"' AND tag='" + sTag + "' AND name='" + sVarName + "'";
				SQLExecDirect(sSQL);
			}
			else
			{
				// row doesn't exist
				sSQL = "INSERT INTO " + sTable + " (player,tag,name,val,expire) VALUES" +
				"('" + sPlayer + "','" + sTag + "','" + sVarName + "','" +
				sValue + "'," + String.valueOf(iExpiration) + ")";
				SQLExecDirect(sSQL);
			}
		}
	
	public static int getPersistentInt(NWObject oObject, String sVarName, String sTable)
	{
	    String sPlayer;
	    String sTag;
	    NWObject oModule = NWObject.MODULE;

	    if (NWScript.getIsPC(oObject))
	    {
	        sPlayer = SQLEncodeSpecialChars(NWScript.getPCPlayerName(oObject));
	        sTag = SQLEncodeSpecialChars(NWScript.getName(oObject,false));
	    }
	    else
	    {
	        sPlayer = "~";
	        sTag = NWScript.getTag(oObject);
	    }

	    sVarName = SQLEncodeSpecialChars(sVarName);

	    String sSQL = "SELECT val FROM " + sTable + " WHERE player='" + sPlayer +
	        "' AND tag='" + sTag + "' AND name='" + sVarName + "'";
	    SQLExecDirect(sSQL);

	    //oModule = GetModule();
	    NWScript.setLocalString(oModule, "NWNX!ODBC!FETCH", "-2147483647");
	    String str = NWScript.getLocalString(oModule, "NWNX!ODBC!FETCH");
	    NWScript.printString("'"+str+"'");
	    return Integer.valueOf(str);
	}
	
}
