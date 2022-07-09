package gentlecoffee;

import gentlecoffee.doctor.coffee.ChangeCommand;
import gentlecoffee.doctor.coffee.Profile;
import gentlecoffee.doctor.coffee.ProfileParamWithLimits;
import gentlecoffee.doctor.coffee.ProfileType;
import gentlecoffee.doctor.coffee.decent.DecentProfile;
import gentlecoffee.doctor.coffee.decent.binary.*;


import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        Path filePath = Path.of("res/profiles/decent/Cremina.json");
        Profile profile = new DecentProfile(
                "default_title",
                "default_author",
                "notes",
                ProfileType.ESPRESSO,
                36.0f,
                18.0f,
                0.0f,
                3);
        System.out.println("title is " + profile.getParameterFromHashCode(profile.TITLE));

        ProfileParamWithLimits title = profile.getParameterFromHashCode(profile.TITLE);
        title.setValue("New  Title");

        profile.changeParameter(new ChangeCommand<>(title));
        System.out.println("title is " + profile.getParameterFromHashCode(profile.TITLE));

        System.out.println("\nChanging a profile from the list index");
        ProfileParamWithLimits nextParam = (ProfileParamWithLimits) profile.getVars().get(1);

        nextParam.setValue("I am the author now");
        System.out.println("author is " + profile.getParameterFromHashCode(profile.AUTHOR));
        profile.changeParameter(new ChangeCommand<>(nextParam));
        System.out.println("author is " + profile.getParameterFromHashCode(profile.AUTHOR));

        System.out.println("\nUndoing Author change");
        profile.undo();
        System.out.println("author is " + profile.getParameterFromHashCode(profile.AUTHOR));

        System.out.println("\nUndoing Title change");
        profile.undo();
        System.out.println("title is " + profile.getParameterFromHashCode(profile.TITLE));
        System.out.println("\nRedoing Title change");
        profile.redo();
        System.out.println("title is " + profile.getParameterFromHashCode(profile.TITLE));


        System.out.println("Testing binary packing");

        ShotDescHeader header = new ShotDescHeader((byte) 6, (byte) 1, 0, 3.5f);
        System.out.println(header);

        FirmwareHeader fwheader = new FirmwareHeader();
        System.out.println(fwheader);

        ShotFrameTail tail = new ShotFrameTail((byte) 32, true, 100);
        System.out.println(tail);

        System.out.println("ShotSample");
        ShotSample sample = new ShotSample();

        System.out.println(sample);
        System.out.println(sample.HeadTemp);
        System.out.println(sample.HeadTemp.get());

        sample.HeadTemp.setRaw(0xAABBCCDD);
        System.out.println(sample);
        System.out.println(sample.HeadTemp);
        System.out.println(sample.HeadTemp.get());


        CalibrationPackage calibrationPackage = new CalibrationPackage();
        calibrationPackage.DE1ReportedVal.value.set(0x7FFFFFFF);
        System.out.println(calibrationPackage);
        System.out.println(calibrationPackage.DE1ReportedVal.get());

    }

}
