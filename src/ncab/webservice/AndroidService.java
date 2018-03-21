package ncab.webservice;

import java.sql.SQLException;
import java.text.ParseException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import ncab.dao.impl.AndroidServiceImpl;


@Path("/AndroidService")
public class AndroidService {

       public AndroidService() {
             // TODO Auto-generated constructor stub
       }

       @POST
       @Produces(MediaType.APPLICATION_JSON)
       @Path("/approval")
       public Response sendApproval(String jsonrequest){
             
             JSONObject jsonreq = new JSONObject();
             JSONObject jsonres = new JSONObject();
             
             try {
                    jsonreq = new JSONObject(jsonrequest);              
             } catch (ParseException e) {
                    e.printStackTrace();
             }
             
             JSONObject jsonresponse = new JSONObject();
       
             int result=0;
             
             AndroidServiceImpl AndroidServiceImpl  = new AndroidServiceImpl();
             
                    String req_id = jsonreq.getString("request_id");
                    
                    String action = jsonreq.getString("Approval");
                    
                                 
                    
                    
                    result=AndroidServiceImpl.saveApproval(action,req_id);       
                    
                          if (result>0) {
                                       
                                        jsonresponse.put("Request_Id", result );
                                        jsonresponse.put("status", "success");
                                       
                                       jsonres = jsonresponse;
                          }else {
                                 return Response.status(200).type("application/json").entity(new JSONObject().put("result", "fail").toString()).build();
                    }
             
                      Response   response = Response.status(200).type("application/json").entity(jsonres.toString()).build();              
             
             return response;
       }
       
}

