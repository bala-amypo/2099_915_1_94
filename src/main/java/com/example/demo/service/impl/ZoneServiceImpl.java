@Override
public Zone createZone(Zone zone) {
    zone.setActive(true);
    return zoneRepository.save(zone);
}
