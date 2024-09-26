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
            rowStringBuilder.append(node.getNodeValue()).append(" ");
        }
        rowStringBuilder.deleteCharAt(rowStringBuilder.length()-1);
        rowStringBuilder.append("\n");

    }

    public void nextPosition(Position position) {
        validatePosition(position);

        nodes[position.getValue()].move(position);
    }

    //특정 사다리 층에서 입력된(현재) 위치 수정 >> '*' 검색해서 삭제 한 후, 입력된 위치 '*'로 입력
    public StringBuilder getRowStringBuilder(){
        removeStarInRowString();

        return rowStringBuilder;
    }
    public StringBuilder getRowStringBuilder(Position position){
        int adjustIndex = 0, count = 0;

        removeStarInRowString();

        for(int i = 0; i*2 + adjustIndex < rowStringBuilder.length(); i++){
            if(count > position.getValue()){
                break;
            }
            count++;
            if(rowStringBuilder.charAt(i*2 + adjustIndex) == '-'){
                adjustIndex++;
            }
        }
        rowStringBuilder.insert(position.getValue() * 2 + 1 + adjustIndex, "*");

        return rowStringBuilder;
    }

    //문자열에 * 찍혀 있으면 삭제
    private void removeStarInRowString(){
        if(rowStringBuilder.indexOf("*") != -1){
            rowStringBuilder.deleteCharAt(rowStringBuilder.indexOf("*"));
        }
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