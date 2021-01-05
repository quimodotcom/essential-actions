package SetCurrentProfile;

import com.StreamPi.ActionAPI.ActionProperty.Property.Property;
import com.StreamPi.ActionAPI.ActionProperty.Property.Type;
import com.StreamPi.ActionAPI.NormalAction.NormalAction;
import com.StreamPi.OBSSuite.Mother.MotherConnection.MotherConnection;
import com.StreamPi.Util.Alert.StreamPiAlert;
import com.StreamPi.Util.Alert.StreamPiAlertType;
import com.StreamPi.Util.Version.Version;

import net.twasi.obsremotejava.OBSRemoteController;
import net.twasi.obsremotejava.callbacks.Callback;
import net.twasi.obsremotejava.requests.ResponseBase;
import net.twasi.obsremotejava.requests.SetCurrentProfile.SetCurrentProfileResponse;

public class SetCurrentProfile extends NormalAction {

    public SetCurrentProfile() {
        setName("Set Current Profile");
        setCategory("OBS");
        setVisibilityInServerSettingsPane(false);
        setAuthor("rnayabed");
        setVersion(new Version(1, 0, 0));
    }

    @Override
    public void initProperties() throws Exception {
        // TODO Auto-generated method stub

        Property currentProfileProperty = new Property("current_profile", Type.STRING);
        currentProfileProperty.setDisplayName("Profile Name");
        currentProfileProperty.setCanBeBlank(false);
        
        addClientProperties(currentProfileProperty);
    }

    @Override
    public void initAction() throws Exception {
        // TODO Auto-generated method stub
    }

    @Override
    public void onActionClicked() throws Exception {
        // TODO Auto-generated method stub

        OBSRemoteController controller = MotherConnection.getRemoteController();

        if (controller == null) {
            new StreamPiAlert("Is OBS Connected?",
                    "It seems there is no connection to OBS, please connect it in Settings", StreamPiAlertType.WARNING)
                            .show();
        } else {
            controller.setCurrentProfile(
                    getClientProperties().getSingleProperty("current_profile").getStringValue(), new Callback() {

                        @Override
                        public void run(ResponseBase response) {
                            // TODO Auto-generated method stub

                            SetCurrentProfileResponse r = (SetCurrentProfileResponse) response;

                            System.out.println("STATUS :"+r.getStatus());
                        }
                
            });
        }
    }

    @Override
    public void onShutDown() throws Exception {
        // TODO Auto-generated method stub

    }
    
}
