package ru.betterstop.beatbox;

import javax.sound.midi.*;


public class MidiPlayer{



    public static void play() {
        MidiPlayer midiPlayer = new MidiPlayer();
        midiPlayer.go();
    }



    public void go(){
        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();

            int[] eventsIWant = {127};
          //  sequencer.addControllerEventListener(BeatBox.myDrawPanel, eventsIWant);

            Sequence sequence = new Sequence(Sequence.PPQ, 4);
            Track track = sequence.createTrack();

            for(int i = 5; i < 61; i += 4){
                track.add(makeEvent(144, 1, i, 100, i));
                track.add(makeEvent(178, 1, 127, 0, i));
                track.add(makeEvent(128, 1, i, 100, i + 2));
            }
            sequencer.setSequence(sequence);
            sequencer.setTempoInBPM(220);
            sequencer.start();
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }

    public static MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) throws InvalidMidiDataException {
        ShortMessage shortMessage = new ShortMessage();
        shortMessage.setMessage(comd, chan, one, two);
        return new MidiEvent(shortMessage, tick);
    }

}
