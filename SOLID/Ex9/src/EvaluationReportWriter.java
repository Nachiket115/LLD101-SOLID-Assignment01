public interface EvaluationReportWriter {
    String write(Submission submission, int plagiarismScore, int codeScore);
}
