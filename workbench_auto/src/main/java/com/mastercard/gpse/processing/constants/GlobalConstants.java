package com.mastercard.gpse.processing.constants;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

/*
 * 
 * @Author: Kamlesh Ahuja @date:Dec 23, 2021
 *
 */
public class GlobalConstants {

	public static final long PAGE_LOADING_TIME_SEC = 45;
	public static final int IMPLICIT_WAIT_TIME_SEC = 60;
	public static final int WAITING_FOR_ELEMENT_PRESENT_SEC = 60;
	public static final int GLOBAL_MAX_SLEEP_TIME = 10000;
	public static final String CLOSE_INSTALMENT_COMMENT="Close Instalment Test";
	public static final String SWITCH_OFF_COMMENT="Switch Off Test";
	public static final String SWITCH_ON_COMMENT="Switch On Test";
	public static final String SET_STATUS_COMMENT="Set Status Test";
	public static final String REDEFINE_COMMENT="Redefine Test";
	public static final String RESTORE_ORIGINAL_COMMENT="RestoreOriginal Test";
	public static final String ACTIVE="Active";
	public static final String REASON_DD_VALUE_BANK_DECISION="Bank decision";
	public static final String REASON_DD_VALUE_FRAUD_RELATED="Fraud related";
	public static final String ACTION_TYPE="Action Type";
	public static final String ADD_OR_UPDATE="Add or Update";
	public static final String OBJECT_TYPE="Object Type";
	public static final String CONTRACT_PARAMETER="Contract Parameter";
	public static final String SWITCH_OFF_1DAY_COMMENT="Switch Off 1 Day Test";
	public static final String FINANCIAL_LIMIT="1000.00";
	public static final String ADDITIONAL="Additional";
	public static final String LIMIT_TYPE="Limit Type";
	public static final String CREDIT_LIMIT_SET_TEMPORARY="Credit Limit (Set Temporary)";
	public static final String MESSAGE="Message";
	public static final String officerGroupName = "INBANK_PCI";
	public static final String COLUMN_NAME = "NAME";
	public static final String PASSWORD = "Password01";
	public static final String OFFICER_TYPE = "Clerk";
	public static final String REASONCODE = "Cardholder request";


	/*public static final int WAITING_FOR_MINIMUM_TIMEOUT_SEC = 7;
	public static final int GLOBAL_SLEEP_TIME = 5000;
	public static final int GLOBAL_MAX_SLEEP_TIME = 10000;


	public static final int WAITING_FOR_FRAME_TO_BE_DISAPPEARED = 10000;

	public static final int POLLING_TIME_5_SEC = 5;
	public static final int WAITING_TIME_2_MIN = 2;*/

	// GDPR MARKETING CONSENTS
	public static final String SFS1_PROFILING = "Yes";
	public static final String SFS2_PARTNERS_OFFERS = "No";
	public static final String SFS3_TRANSFER_DATA = "Yes";
	public static final String BNP1_PROFILING = "No";
	public static final String BNP2_PARTNERS_OFFERS = "Yes";

	// Contract Main Screen - Financials - Financial Details - Accounts - Details
	public static final String NAME = "BCC Current Retail";

	// Transaction Builders
	public static final String CREDIT_TRANSACTION = "CreditTransaction";
	public static final String DEBIT_TRANSACTION = "DebitTransaction";
	public static final String DEBIT_CONTRACT = "DebitContract";
	public static final String STANDARD_MEMO = "Standard Memo";
}
