package be.company.fca.controller;

import be.company.fca.utils.FileUtils;
import be.e_contract.dssp.client.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;

@RestController
public class SignatureController {

    @RequestMapping(value = "/api/signedFile", method = RequestMethod.POST)
    public void signedFile(HttpSession httpSession, @RequestParam(name="SignResponse") String signResponse) throws Exception {
        DigitalSignatureServiceSession session = (DigitalSignatureServiceSession) httpSession.getAttribute("DigitalSignatureServiceSession");
        SignResponseVerifier.checkSignResponse(signResponse, session);

        DigitalSignatureServiceClient client = new DigitalSignatureServiceClient("https://www.e-contract.be/dss-ws/dss");
        byte[] signedDocument = client.downloadSignedDocument(session);

        FileOutputStream outputStream = new FileOutputStream("D:/eContractSignedFile.pdf");
        outputStream.write(signedDocument);
        outputStream.close();

        httpSession.removeAttribute("DigitalSignatureServiceSession");

        System.err.println("Fichier signe recupere");

//        return "redirect:/signFile.zul";
    }

    @RequestMapping(value = "/api/signFile")
    public String signFile(HttpSession httpSession) throws Exception {

        String src = "D:/test.pdf";

        DigitalSignatureServiceClient client = new DigitalSignatureServiceClient("https://www.e-contract.be/dss-ws/dss");
        byte[] fileData = FileUtils.convertToByteArray(new FileInputStream(src));
        //TODO : doesn't work with these mimetype
//        DigitalSignatureServiceSession session = client.uploadDocument("text/plain", fileData);
//        DigitalSignatureServiceSession session = client.uploadDocument("image/png", fileData);

        // OK with these mimetype
        DigitalSignatureServiceSession session = client.uploadDocument("application/pdf", fileData);
//        DigitalSignatureServiceSession session = client.uploadDocument("text/xml", fileData);
//        DigitalSignatureServiceSession session = client.uploadDocument("application/zip", fileData);


        httpSession.setAttribute("DigitalSignatureServiceSession", session);

        VisibleSignatureConfiguration visibleSignatureConfiguration = new VisibleSignatureConfiguration();
        visibleSignatureConfiguration.setVisibleSignaturePosition(1,100,100, VisibleSignatureProfile.eID_PHOTO);
        String destination = "http://localhost:9100/api/signedFile";
        String pendingRequest = PendingRequestFactory.createPendingRequest(session, destination, "fr", visibleSignatureConfiguration);

        PrintWriter out = new PrintWriter("D:/pendingRequest.txt");
        out.write(pendingRequest);
        out.close();
        System.err.println("pendingRequest : " + pendingRequest);

        return pendingRequest;

    }

}
