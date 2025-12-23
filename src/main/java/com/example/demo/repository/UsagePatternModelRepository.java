public interface UsagePatternModelRepository
        extends JpaRepository<UsagePatternModel, Long> {

    Optional<UsagePatternModel> findTop1ByBinOrderByLastUpdatedDesc(Bin bin);
}
