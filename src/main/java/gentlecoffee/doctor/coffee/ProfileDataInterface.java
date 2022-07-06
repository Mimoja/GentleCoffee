package gentlecoffee.doctor.coffee;

interface ProfileDataInterface<SELF extends ProfileDataInterface<SELF>> {
    public SELF merge(SELF r);
}
