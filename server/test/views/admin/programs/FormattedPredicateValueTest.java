package views.admin.programs;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.common.collect.ImmutableList;
import java.util.Optional;
import org.junit.Test;
import services.applicant.question.Scalar;
import services.program.predicate.LeafOperationExpressionNode;
import services.program.predicate.Operator;
import services.program.predicate.PredicateValue;

public class FormattedPredicateValueTest {

  @Test
  public void format_currency() {
    LeafOperationExpressionNode node =
        LeafOperationExpressionNode.builder()
            .setQuestionId(1)
            .setScalar(Scalar.CURRENCY_CENTS)
            .setOperator(Operator.EQUAL_TO)
            .setComparedValue(PredicateValue.of(1234))
            .build();
    FormattedPredicateValue actual = FormattedPredicateValue.fromLeafNode(Optional.of(node));
    FormattedPredicateValue expected =
        FormattedPredicateValue.create(Optional.of("12.34"), Optional.empty());
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void format_ageBetween() {
    LeafOperationExpressionNode node =
        LeafOperationExpressionNode.builder()
            .setQuestionId(1)
            .setScalar(Scalar.DATE)
            .setOperator(Operator.AGE_BETWEEN)
            .setComparedValue(PredicateValue.pairOfLongs(18, 30))
            .build();
    FormattedPredicateValue actual = FormattedPredicateValue.fromLeafNode(Optional.of(node));
    FormattedPredicateValue expected =
        FormattedPredicateValue.create(Optional.of("18"), Optional.of("30"));
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void format_ageBetween_legacy() {
    LeafOperationExpressionNode node =
        LeafOperationExpressionNode.builder()
            .setQuestionId(1)
            .setScalar(Scalar.DATE)
            .setOperator(Operator.AGE_BETWEEN)
            .setComparedValue(PredicateValue.listOfLongs(ImmutableList.of(18L, 30L)))
            .build();
    FormattedPredicateValue actual = FormattedPredicateValue.fromLeafNode(Optional.of(node));
    FormattedPredicateValue expected =
        FormattedPredicateValue.create(Optional.of("18"), Optional.of("30"));
    assertThat(actual).isEqualTo(expected);
  }
}
