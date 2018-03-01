package be.company.fca.view;

import be.company.fca.utils.FileUtils;
import be.e_contract.dssp.client.*;
import be.e_contract.dssp.client.exception.ClientRuntimeException;
import be.e_contract.dssp.client.exception.SubjectNotAuthorizedException;
import be.e_contract.dssp.client.exception.UserCancelException;
import org.apache.xml.security.exceptions.Base64DecodingException;
import org.springframework.util.StringUtils;
import org.xml.sax.SAXException;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.*;

import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.nio.file.Files;

public class SignFileWindow extends Window implements AfterCompose {

    private String DIGITAL_SIGNATURE_KEY = "DigitalSignatureServiceSession";
    private String MIMETYPE_KEY = "mimetypeKey";
    private String FILENAME_KEY = "filenameKey";

    private DigitalSignatureServiceClient client = new DigitalSignatureServiceClient("https://www.e-contract.be/dss-ws/dss");
//    private DigitalSignatureServiceClient client = new DigitalSignatureServiceClient("https://doccle.e-contract.be/dss-ws/dss");


    public SignFileWindow() {
        client.setCredentials("spw-esignbox","9380fdca");
    }

    private class SignatureSpecs {
        int pageNumber = 1;
        int xPosition = 100;
        int yPosition  = 100;
        VisibleSignatureProfile visibleSignatureProfile = VisibleSignatureProfile.eID_PHOTO;

    }

    private SignatureSpecs getSignatureSpecs(){
        SignatureSpecs signatureSpecs = new SignatureSpecs();

        Intbox pageNumberBox = (Intbox) getFellow("pageNumber");
        Intbox xPositionBox = (Intbox) getFellow("xPosition");
        Intbox yPositionBox = (Intbox) getFellow("yPosition");
        Checkbox photoBox = (Checkbox) getFellow("photoBox");

        if (pageNumberBox.getValue()!=null){
            signatureSpecs.pageNumber = pageNumberBox.getValue();
        }

        if (xPositionBox.getValue()!=null){
            signatureSpecs.xPosition = xPositionBox.getValue();
        }

        if (yPositionBox.getValue()!=null){
            signatureSpecs.yPosition = yPositionBox.getValue();
        }

        if (!photoBox.isChecked()){
            signatureSpecs.visibleSignatureProfile = VisibleSignatureProfile.eID_PHOTO_SIGNER_INFO;
        }else{
            signatureSpecs.visibleSignatureProfile = VisibleSignatureProfile.eID_PHOTO;
        }

        return signatureSpecs;
    }

    @Override
    public void afterCompose() {

        Fileupload fileupload = (Fileupload) getFellow("fileuploadBtn");
        fileupload.addEventListener(Events.ON_UPLOAD, new EventListener<Event>() {
            @Override
            public void onEvent(Event event) throws Exception {
                UploadEvent uploadEvent = (UploadEvent) event;
                Media media = uploadEvent.getMedia();
                if (media!=null){
                    byte[] fileData = null;
                    if (media.isBinary()){
                        fileData = media.getByteData();
                    }else{
                        fileData = media.getStringData().getBytes();
                    }

                    //TODO : doesn't work with these mimetype
    //        DigitalSignatureServiceSession session = client.uploadDocument("text/plain", fileData);
    //        DigitalSignatureServiceSession session = client.uploadDocument("image/png", fileData);

                    // OK with these mimetype
                    DigitalSignatureServiceSession session = client.uploadDocument(media.getContentType(), fileData);
    //        DigitalSignatureServiceSession session = client.uploadDocument("text/xml", fileData);
    //        DigitalSignatureServiceSession session = client.uploadDocument("application/zip", fileData);

                    Executions.getCurrent().getSession().setAttribute(DIGITAL_SIGNATURE_KEY, session);
                    Executions.getCurrent().getSession().setAttribute(MIMETYPE_KEY, media.getContentType());
                    Executions.getCurrent().getSession().setAttribute(FILENAME_KEY, media.getName());

                    VisibleSignatureConfiguration visibleSignatureConfiguration = new VisibleSignatureConfiguration();
                    SignatureSpecs signatureSpecs = getSignatureSpecs();
                    visibleSignatureConfiguration.setVisibleSignaturePosition(signatureSpecs.pageNumber,signatureSpecs.xPosition,signatureSpecs.yPosition, signatureSpecs.visibleSignatureProfile);
                    visibleSignatureConfiguration.setCustomText("This is my signature");
                    String destination = "http://localhost:9100/signFile.zul";
                    String pendingRequest = PendingRequestFactory.createPendingRequest(session, destination, "fr", visibleSignatureConfiguration);

                    if (!StringUtils.isEmpty(pendingRequest)){
                        ((Textbox) getFellow("pendingRequest_txt")).setValue(pendingRequest);
                        Clients.submitForm("BrowserPostForm");
                    }
                }
            }
        });

        try{
            String signResponse = Executions.getCurrent().getParameter("SignResponse");
            if (!StringUtils.isEmpty(signResponse)){

                Session httpSession = Executions.getCurrent().getSession();

                DigitalSignatureServiceSession session = (DigitalSignatureServiceSession) httpSession.getAttribute(DIGITAL_SIGNATURE_KEY);
                String mimeType = (String) httpSession.getAttribute(MIMETYPE_KEY);
                String filename = (String) httpSession.getAttribute(FILENAME_KEY);

                if (session!=null){
                    SignResponseVerifier.checkSignResponse(signResponse, session);

                    byte[] signedDocument = client.downloadSignedDocument(session);

                    getFellow("successDiv").setVisible(true);

                    Filedownload.save(signedDocument,mimeType,"signed." + filename);

                    httpSession.removeAttribute(DIGITAL_SIGNATURE_KEY);
                    httpSession.removeAttribute(MIMETYPE_KEY);

//                    VerificationResult verificationResult = client.verify("application/pdf", signedDocument);
//
//                    System.err.println("Fichier verifie");
                }

            }
        }catch (Exception e){
            e.printStackTrace(System.err);
        }
    }
}
