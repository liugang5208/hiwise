package com.sky.hiwise.algorithms.leetcode.lcp;

public class ProgrammableRobot03 {
    /**
     * LCP 03. 机器人大冒险
     * 力扣团队买了一个可编程机器人，机器人初始位置在原点(0, 0)。小伙伴事先给机器人输入一串指令command，机器人就会无限循环这条指令的步骤进行移动。指令有两种：
     * U: 向y轴正方向移动一格
     * R: 向x轴正方向移动一格。
     * 不幸的是，在 xy 平面上还有一些障碍物，他们的坐标用obstacles表示。机器人一旦碰到障碍物就会被损毁。
     * 给定终点坐标(x, y)，返回机器人能否完好地到达终点。如果能，返回true；否则返回false。
     * 示例 1：
     * 输入：command = "URR", obstacles = [], x = 3, y = 2
     * 输出：true
     * 解释：U(0, 1) -> R(1, 1) -> R(2, 1) -> U(2, 2) -> R(3, 2)。
     * 示例 2：
     * 输入：command = "URR", obstacles = [[2, 2]], x = 3, y = 2
     * 输出：false
     * 解释：机器人在到达终点前会碰到(2, 2)的障碍物。
     * 示例 3：
     * 输入：command = "URR", obstacles = [[4, 2]], x = 3, y = 2
     * 输出：true
     * 解释：到达终点后，再碰到障碍物也不影响返回结果。
     */
    public boolean robot(String command, int[][] obstacles, int x, int y) {
        if (command == null || command.length() == 0) {
            return false;
        }
        int currX = 0, currY = 0;
        char[] commands = command.toCharArray();
        int xCnt = 0, yCnt = 0;
        for (char c : commands) {
            if (c == 'U') {
                yCnt++;
            } else if (c == 'R') {
                xCnt++;
            }
        }
        if (!canReach(xCnt, yCnt, command, x, y)) {
            return false;
        }
        for (int[] obs: obstacles){
            if (obs[0] <= x && obs[1] <= y && canReach(xCnt, yCnt, command, obs[0], obs[1])) {
                return false;
            }
        }
        return true;
    }

    public boolean canReach(int xCnt, int yCnt, String command, int destX, int destY) {
        int loop = Math.min(destX / xCnt, destY / yCnt);
        destX -= loop * xCnt;
        destY -= loop * yCnt;
        for (char c : command.toCharArray()) {
            if (destX == 0 && destY == 0 ) {
                return true;
            }
            if (c == 'U') {
                destY--;
            } else {
                destX--;
            }
        }
        return destX == 0 && destY == 0;
    }
}
