@Override
public Bin createBin(Bin bin) {
    if (bin.getCapacityLiters() == null || bin.getCapacityLiters() <= 0) {
        throw new BadRequestException("Invalid capacity");
    }

    Zone zone = zoneRepository.findById(bin.getZone().getId())
            .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));

    if (!zone.getActive()) {
        throw new BadRequestException("inactive");
    }

    bin.setZone(zone);
    if (bin.getActive() == null) {
        bin.setActive(true);
    }
    return binRepository.save(bin);
}
