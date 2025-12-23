@Override
public OverflowPrediction generatePrediction(Long binId) {

    Bin bin = binRepository.findById(binId)
            .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));

    FillLevelRecord record = recordRepository
            .findTop1ByBinOrderByRecordedAtDesc(bin)
            .orElseThrow(() -> new BadRequestException("No fill records"));

    UsagePatternModel model = modelRepository
            .findTop1ByBinOrderByLastUpdatedDesc(bin)
            .orElseThrow(() -> new BadRequestException("No usage model"));

    double remaining = 100 - record.getFillPercentage();
    int days = (int) Math.ceil(remaining / model.getAvgDailyIncreaseWeekday());

    OverflowPrediction prediction = new OverflowPrediction();
    prediction.setBin(bin);
    prediction.setModelUsed(model);
    prediction.setDaysUntilFull(days);
    prediction.setPredictedFullDate(LocalDate.now().plusDays(days));

    return predictionRepository.save(prediction);
}
