@Override
public UsagePatternModel createModel(UsagePatternModel model) {
    Bin bin = binRepository.findById(model.getBin().getId())
            .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));

    if (model.getAvgDailyIncreaseWeekday() < 0 ||
        model.getAvgDailyIncreaseWeekend() < 0) {
        throw new BadRequestException("Negative values not allowed");
    }

    model.setBin(bin);
    model.setLastUpdated(LocalDateTime.now());
    return modelRepository.save(model);
}
