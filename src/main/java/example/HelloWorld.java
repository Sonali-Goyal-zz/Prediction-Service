package example;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Created by sgoyal5 on 7/12/15.
 */
// The Java class will be hosted at the URI path "/helloworld"
@Path("/prediction")
public class HelloWorld {
    // The Java method will process HTTP GET requests
    private static String inputArray1;

    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Path("/predict")
    @Produces("text/json")
    public double[] getClichedMessage() {
        System.out.println(inputArray1);
        // Return some cliched textual content
        String[] items = inputArray1.split(",");


        int[] results = new int[items.length];


        for (int i = 0; i < items.length; i++) {
            try {
                results[i] = Integer.parseInt(items[i]);

            } catch (NumberFormatException nfe) {
            }
            ;
        }
        HoltWinters k = new HoltWinters();
        //int[] intArray = new int[8];
        double[] forecast;


        forecast = k.forecast(results, 0.1, 0.2, 0.3, 4, 4, false);

        return forecast;
    }

    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Path("/predict1")
    @Produces("text/json")
    public double[] getClichedMessage1() {
        System.out.println(inputArray1);
        // Return some cliched textual content
        String[] items = inputArray1.split(",");


        double[] results1 = new double[items.length];

        for (int i = 0; i < items.length; i++) {
            try {

                results1[i] = Double.parseDouble(items[i]);
            } catch (NumberFormatException nfe) {
            }
            ;
        }
        HoltWinters k = new HoltWinters();
        //int[] intArray = new int[8];

        double[] forecast1;
        forecast1 = k.doubleExponentialForecast(results1, 0.1, 0.2, 1, 7);

        return forecast1;

    }

    @POST
    @Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    public Response getData(String data) {
        inputArray1 = data;
        System.out.println(data);
        System.out.println(inputArray1);
        return Response.status(200).entity(data).build();
    }
}

