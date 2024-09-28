package ladder;

import static ladder.Direction.*;

public class Row {
    private final Node[] nodes;
    private StringBuilder rowStringBuilder;

    public Row(GreaterThanOne numberOfPerson) {
        nodes = new Node[numberOfPerson.getNumber()];
        for (int i = 0; i < numberOfPerson.getNumber(); i++) {
            nodes[i] = Node.from(NONE);
        }
    }

    public void drawLine(Position startPosition) {
        validateDrawLinePosition(startPosition);

        setDirectionBetweenNextPosition(startPosition);
    }

    //사다리 한 개 층 문자열 생성
    public void setRowString(){
        rowStringBuilder = new StringBuilder();

        for (Node node : nodes) {
            node.setNodeString(rowStringBuilder);                           //append(nodos.getValue()) 보다, node에 sb 넘겨줘서 처리
        }
        rowStringBuilder.deleteCharAt(rowStringBuilder.length()-1);
        rowStringBuilder.append("\n");

    }

    public void nextPosition(Position position) {
        validatePosition(position);

        nodes[position.getValue()].move(position);
    }

    // '*'를 제거하고, '*' 없는 문자열 반환
    public StringBuilder getRowStringBuilder() {
        clearStarInRowString();

        return rowStringBuilder;
    }

    // '*'를 제거하고, 새 위치에 '*'를 추가하여 문자열 반환
    public StringBuilder getRowStringBuilder(Position position) {
        clearStarInRowString();
        insertStarAtPosition(position);

        return rowStringBuilder;
    }

    // 문자열에서 '*'를 제거하는 메서드
    private void clearStarInRowString() {
        int starIndex = rowStringBuilder.indexOf("*");

        if (starIndex != -1) {
            rowStringBuilder.deleteCharAt(starIndex);
        }
    }

    // 주어진 위치에 '*'를 삽입하는 메서드
    private void insertStarAtPosition(Position position) {
        int offset = calculateOffsetForStar(position);

        rowStringBuilder.insert(position.getValue() * 2 + 1 + offset, "*");
    }

    // '*'가 삽입될 위치를 계산하는 메서드
    private int calculateOffsetForStar(Position position) {
        int offset = 0;
        int col = 0;

        // '*'가 들어갈 위치에 '-'가 있으면, 그만큼 offset을 증가
        for (int i = 0; i * 2 + offset < rowStringBuilder.length(); i++) {
            if (col > position.getValue()) {
                break;
            }
            col++;
            if (rowStringBuilder.charAt(i * 2 + offset) == '-') {
                offset++;
            }
        }
        return offset;
    }

    private void setDirectionBetweenNextPosition(Position position) {
        nodes[position.getValue()].setRightNode();
        position.next();
        nodes[position.getValue()].setLeftNode();
    }

    public void validatePosition(Position position) {                      //일단 public으로 수정하여 진행
        if (isInvalidPosition(position) ) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_POSITION.getMessage());
        }
    }

    private void validateDrawLinePosition(Position startPosition) {
        validatePosition(startPosition);
        if (isLineAtPosition(startPosition) || isLineAtNextPosition(startPosition)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_DRAW_POSITION.getMessage());
        }
    }

    private boolean isInvalidPosition(Position position) {
        return position.isBiggerThan(nodes.length - 1) ;
    }

    private boolean isLineAtPosition(Position position) {
        return nodes[position.getValue()].isAlreadySetDirection();
    }

    private boolean isLineAtNextPosition(Position position) {
        position.next();
        boolean lineAtPosition = isLineAtPosition(position);
        position.prev();
        return lineAtPosition;
    }

}