package org.szi.kafka;

import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;

public class GitHubSchemas {

    // szi to see the json 2 be modeled issue
    // curl -s -X GET https://api.github.com/repos/kubernetes/kubernetes/issues | jq '.[0]'

    public static String NEXT_PAGE_FIELD = "next_page";

    // Issue fields (top) //selective
    public static String OWNER_FIELD = "owner";  //not par of response json but of connector config
    public static String REPOSITORY_FIELD = "repository";   //not par of response json but of connector config
    public static String CREATED_AT_FIELD = "created_at";
    public static String UPDATED_AT_FIELD = "updated_at";
    public static String NUMBER_FIELD = "number";
    public static String URL_FIELD = "url";
    public static String HTML_URL_FIELD = "html_url";
    public static String TITLE_FIELD = "title";
    public static String STATE_FIELD = "state";

    // user sub object fields
    public static String USER_FIELD = "user"; //key of sub object
    public static String USER_URL_FIELD = "url";
    public static String USER_HTML_URL_FIELD = "html_url";
    public static String USER_ID_FIELD = "id";
    public static String USER_LOGIN_FIELD = "login";

    // pr (pull_request) sub Object fields
    public static String PR_FIELD = "pull_request"; //key of sub object
    public static String PR_URL_FIELD = "url";
    public static String PR_HTML_URL_FIELD = "html_url";

    // Schema names //these are names for connector outgoing messages
    public static String SCHEMA_KEY = "issue_key";
    public static String SCHEMA_VALUE_ISSUE = "issue";
    public static String SCHEMA_VALUE_USER = "user";
    public static String SCHEMA_VALUE_PR = "pr";

    // kafka message key schema
    public static Schema KEY_SCHEMA = SchemaBuilder.struct().name(SCHEMA_KEY)
            .version(1)
            .field(OWNER_FIELD, Schema.STRING_SCHEMA)
            .field(REPOSITORY_FIELD, Schema.STRING_SCHEMA)
            .field(NUMBER_FIELD, Schema.INT32_SCHEMA)
            .build();


    public static Schema USER_SCHEMA = SchemaBuilder.struct().name(SCHEMA_VALUE_USER)
            .version(1)
            .field(USER_URL_FIELD, Schema.STRING_SCHEMA)
            .field(USER_ID_FIELD, Schema.INT32_SCHEMA)
            .field(USER_LOGIN_FIELD, Schema.STRING_SCHEMA)
            .build();

    // optional schema
    public static Schema PR_SCHEMA = SchemaBuilder.struct().name(SCHEMA_VALUE_PR)
            .version(1)
            .field(PR_URL_FIELD, Schema.STRING_SCHEMA)
            .field(PR_HTML_URL_FIELD, Schema.STRING_SCHEMA)
            .optional() // !NB   not mandatory
            .build();

    //kafka message value schema  is a nested schema
    public static Schema VALUE_SCHEMA = SchemaBuilder.struct().name(SCHEMA_VALUE_ISSUE)
            .version(1)
            .field(URL_FIELD, Schema.STRING_SCHEMA)
            .field(TITLE_FIELD, Schema.STRING_SCHEMA)
            .field(CREATED_AT_FIELD, Schema.INT64_SCHEMA)
            .field(UPDATED_AT_FIELD, Schema.INT64_SCHEMA)
            .field(NUMBER_FIELD, Schema.INT32_SCHEMA)
            .field(STATE_FIELD, Schema.STRING_SCHEMA)
            .field(USER_FIELD, USER_SCHEMA) // composed, mandatory schema defined above
            .field(PR_FIELD, PR_SCHEMA)     // composed optional
            .build();
}
