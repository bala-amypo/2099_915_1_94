@Override
public FillLevelRecord createRecord(FillLevelRecord record) {
    Bin bin = binRepository.findById(record.getBin().getId())
            .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));

    if (!bin.getActive()) {
        throw new BadRequestException("Bin inactive");
    }

    if (record.getRecordedAt().isAfter(LocalDateTime.now())) {
        throw new BadRequestException("Future date not allowed");
    }

    record.setBin(bin);
    return recordRepository.save(record);
}
