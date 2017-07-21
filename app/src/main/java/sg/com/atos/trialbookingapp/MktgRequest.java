package sg.com.atos.trialbookingapp;

/**
 * Created by hasan on 18/7/2017.
 */
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MktgRequest  extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://www.atos.com.sg/api/crm/marketing_and.php";
    private Map<String, String> params;

    public MktgRequest(Response.Listener<String> listener) {
        super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
