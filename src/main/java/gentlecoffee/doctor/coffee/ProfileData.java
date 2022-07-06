package gentlecoffee.doctor.coffee;

public record ProfileData (
        String title, 
        String author,
        String notes,
        ProfileType type,
        Float targetBrewWeight,
        Float inputWeight
) implements ProfileDataInterface<ProfileData> {
    public ProfileData() {
        this(null, null, null, null, null, null);
    }

    public ProfileData merge(ProfileData r) {
        return new ProfileData(
            r.title() != null ? r.title() : this.title(),
            r.author() != null ? r.author() : this.author(),
            r.notes() != null ? r.notes() : this.notes(),
            r.type() != null ? r.type() : this.type(),
            r.targetBrewWeight() != null ? r.targetBrewWeight() : this.targetBrewWeight(),
            r.inputWeight() != null ? r.inputWeight() : this.inputWeight()
        );
    }
}

