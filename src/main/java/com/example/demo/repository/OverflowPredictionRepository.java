public interface OverflowPredictionRepository
        extends JpaRepository<OverflowPrediction, Long> {

    List<OverflowPrediction> findLatestPredictionsForZone(Zone zone);
}
