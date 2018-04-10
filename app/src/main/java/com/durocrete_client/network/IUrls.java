package com.durocrete_client.network;

/**
 * Created by Anil Sharma and Swapnil Jadhav on 1/2/16.
 */
public interface IUrls {

    String BASE_URL = "http://210.16.103.150:83/PuneDuroCrete/";

    //client
    String check_client_Mobile = BASE_URL + "check_mobile.php";
    String Sign_in = BASE_URL + "sign_in.php";
    String get_client_by_clientID = BASE_URL + "get_client_details.php";
    String Update_client = BASE_URL + "update_client.php";
    String Client_Info = BASE_URL + "get_sites_byCL.php";     //by using client id
    String site_details = BASE_URL + "get_site_details_by_site.php"; //by using site id
    String GET_Material = BASE_URL + "get_materials.php";
    String GET_Material_Enquiry = BASE_URL + "get_material_enquiry.php";
    String GET_Material_type_Aggratte = BASE_URL + "get_material_aggregate.php";
    String GET_Tests = BASE_URL + "get_material_test.php";
    String GET_Newregistration = BASE_URL + "client_contact_check.php";
    String get_client_by_site = BASE_URL + "get_client_by_site.php";
    String Update_site = BASE_URL + "update_site.php";
    String get_site_details = BASE_URL + "get_site_details.php";
    String GET_ENQUIRYID = BASE_URL + "enquiry_post.php";
    String Add_client = BASE_URL + "add_new_client.php";
    String qoutation = BASE_URL + "quatation_post.php";
    String Enquiry_by_site = BASE_URL + "get_enquiry_bysite.php";
    String get_enquiry_material_test = BASE_URL + "get_enquiry.php";
    String get_report = BASE_URL + "get_report.php";
    String tets_request = BASE_URL + "test_request_post.php";
    String Mixmaterial_list = BASE_URL + "get_material_list.php";
    String feedback = BASE_URL + "feedback.php";

    String mix_design_post = BASE_URL + "mix_design_post.php";
    String mix_design_info = BASE_URL + "mix_design_info.php";
    String get_client_info_form_clientID = BASE_URL + "get_client.php";
    String Enquiry_form = BASE_URL + "enquiry_submit_clients.php";
    String Enquiry_Submit = "http://210.16.103.150:83/PuneDuroCrete/enquiry_submit.php";
    String Test_Qoutation = "http://210.16.103.150:83/PuneDuroCrete/test_quotation.php";

}