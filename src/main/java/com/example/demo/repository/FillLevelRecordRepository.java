public interface FillLevelRecordRepository
        extends JpaRepository<FillLevelRecord, Long> {

    Optional<FillLevelRecord> findTop1ByBinOrderByRecordedAtDesc(Bin bin);

    List<FillLevelRecord> findByBinOrderByRecordedAtDesc(Bin bin);

    List<FillLevelRecord> findByBinAndRecordedAtBetween(
            Bin bin, LocalDateTime start, LocalDateTime end);
}
