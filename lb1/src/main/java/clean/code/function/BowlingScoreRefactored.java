package clean.code.function;

import java.util.ArrayList;
import java.util.List;

public class BowlingScoreRefactored {

    private static final int FRAMES_COUNT = 10;
    private static final String FRAMES_DELIMITER = " ";


    static class Frame {
        char first;
        char second;

        public Frame(char first, char second) {
            this.first = first;
            this.second = second;
        }

        boolean isStrike() {
            return 'X' == first;
        }

        int addCurrent(int currentScore, char currentChar) {
            int score = currentScore;
            if ('X' == currentChar) {
                score += 10;
            } else {
                score = addIfNotDash(score, currentChar);
            }
            return score;
        }

        int addIfNotDash(int currentScore, char char_) {
            int score = currentScore;
            if ('-' != char_) {
                score += Character.getNumericValue(char_);
            }
            return score;
        }


        int addScore(int i, int currentScore, Frame nextFrame, Frame nextNextFrame) {
            int score = currentScore;
            if ('/' == second) {
                score += 10;
                if (nextFrame.isStrike()) {
                    score += 10;
                } else {
                    score = addIfNotDash(score, nextFrame.first);
                }
            } else {
                score = addIfNotDash(score, first);
                score = addIfNotDash(score, second);
            }
            return score;
        }
    }

    static class Strike extends Frame {

        public Strike(char first) {
            super(first, ' ');
        }

        @Override
        int addScore(int i, int currentScore, Frame nextFrame, Frame nextNextFrame) {
            int score = currentScore;
            score += 10;
            if (i + 1 == FRAMES_COUNT - 1) {
                score = addCurrent(score, nextFrame.first);
                score = addCurrent(score, nextFrame.second);
            } else {
                if (nextFrame.isStrike()) {
                    score += 10;
                    if (i + 2 != FRAMES_COUNT - 1) {
                        if (nextNextFrame.isStrike()) {
                            score += 10;
                        } else {
                            if ('/' == nextNextFrame.second) {
                                score += 10;
                            } else {
                                score = addIfNotDash(score, nextNextFrame.first);
                            }
                        }
                    } else {
                        score = addCurrent(score, nextNextFrame.first);
                    }
                } else {
                    if ('/' == nextFrame.second) {
                        score += 10;
                    } else {
                        score = addIfNotDash(score, nextFrame.first);
                        score = addIfNotDash(score, nextFrame.second);
                    }
                }
            }
            return score;
        }
    }

    static class BonusFrame extends Frame {
        char bonus;

        public BonusFrame(char first, char second, char bonus) {
            super(first, second);
            this.bonus = bonus;
        }

        boolean hasBonus() {
            return bonus != ' ';
        }

        int addBonusIfPresent(int currentScore) {
            int score = currentScore;
            if (hasBonus()) {
                score = addCurrent(score, bonus);
            }
            return score;
        }

        @Override
        int addScore(int i, int currentScore, Frame nextFrame, Frame nextNextFrame) {
            int score = currentScore;
            if (isStrike()) {
                score += 10;
                score = addCurrent(score, second);
                score = addBonusIfPresent(score);
            } else if ('/' == second) {
                score += 10;
                score = addBonusIfPresent(score);
            } else {
                score = addIfNotDash(score, first);
                score = addIfNotDash(score, second);
            }
            return score;
        }
    }

    static class FrameFactory {
        private static Frame createFrame(String frameStr) {
            return switch (frameStr.length()) {
                case 1 -> new Strike(frameStr.charAt(0));
                case 2 -> new Frame(frameStr.charAt(0), frameStr.charAt(1));
                case 3 -> new BonusFrame(frameStr.charAt(0), frameStr.charAt(1), frameStr.charAt(2));
                default -> throw new IllegalArgumentException("Wrong frame format");
            };
        }

        static List<Frame> getFrames(String framesString) {
            List<Frame> frames = new ArrayList<>(FRAMES_COUNT);
            String[] framesSplit = framesString.split(FRAMES_DELIMITER);
            for (int i = 0; i < FRAMES_COUNT; i++) {
                String currentFrameStr = framesSplit[i];
                //to be last item is always a bonus frame
                currentFrameStr = currentFrameStr.length() != 3 && i == FRAMES_COUNT - 1 ? currentFrameStr + " " : currentFrameStr;
                Frame frame = FrameFactory.createFrame(currentFrameStr);
                frames.add(frame);
            }
            return frames;
        }
    }

    static class FramesManager {
        final List<Frame> frames;

        FramesManager(List<Frame> frames) {
            this.frames = frames;
        }

        private Frame get(int index) {
            if (index > frames.size() - 1) {
                return null;
            }
            return frames.get(index);
        }

        Frame getNext(int index) {
            return get(index + 1);
        }

        Frame getNextNext(int index) {
            return get(index + 2);
        }

    }

    public static int score(String framesStr) {
        int score = 0;
        List<Frame> frames = FrameFactory.getFrames(framesStr);
        FramesManager manager = new FramesManager(frames);
        for (int i = 0; i < FRAMES_COUNT; i++) {
            Frame currentFrame = frames.get(i);
            Frame nextFrame = manager.getNext(i);
            Frame nextNextFrame = manager.getNextNext(i);
            score = currentFrame.addScore(i, score, nextFrame, nextNextFrame);
        }
        return score;
    }
}
