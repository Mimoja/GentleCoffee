package gentlecoffee;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gentlecoffee.doctor.coffee.ChangeCommand;
import gentlecoffee.doctor.coffee.Profile;
import gentlecoffee.doctor.coffee.decent.DecentProfile;
import gentlecoffee.doctor.coffee.decent.binary.FirmwareHeader;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        Path filePath = Path.of("res/profiles/decent/Cremina.json");
        Profile profile = null;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            byte[] bytes = Files.readAllBytes(filePath);
            String fileContent = new String(bytes);

            profile = DecentProfile.fromString(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("title is " + profile.title);

        profile.changeParameter(new ChangeCommand<String>("title", "hahahahaha"));

        System.out.println("title is " + profile.title);
        System.out.println("Undoing last change");

        profile.undo();
        System.out.println("title is " + profile.title);
        System.out.println("Redoing last change");

        profile.redo();
        System.out.println("title is " + profile.title);

        FirmwareHeader header = new FirmwareHeader();
        header.Version.set(0x11223344);
        System.out.println(header);
    }

}
