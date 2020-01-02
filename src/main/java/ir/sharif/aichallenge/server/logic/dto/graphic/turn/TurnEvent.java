package ir.sharif.aichallenge.server.logic.dto.graphic.turn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TurnEvent {
    private boolean isAlive;
    private int ap;
    private int hp;
    private List<Integer> hand;
    private List<GraphicUnit> units;
    private List<MapSpell> mapSpells;

    public static TurnEvent getGraphicTurnEvent(Player player, Game game) {
        TurnEvent turnEvent = new TurnEvent();
        King king = game.getKingWithId(player.getId());

        assert (king.getMainUnit().getPlayer().getId() == player.getId());

        turnEvent.setAlive(king.getHealthComponent().getHealth() > 0);
        turnEvent.setAp(player.getAp());
        turnEvent.setHand(player.getHandIds());
        turnEvent.setHp(king.getHealthComponent().getHealth());
        turnEvent.setUnits(getGraphicUnits(player, game));
        turnEvent.setMapSpells(getGraphicMapSpells(player, game));

        return turnEvent;
    }

    private static List<GraphicUnit> getGraphicUnits(Player player, Game game) {
        ArrayList<GraphicUnit> graphicUnits = new ArrayList<>();
        for (Unit unit : game.getUnitsWithId().values()) {
            if(unit.getPlayer().getId() != player.getId()) continue ;
            GraphicUnit graphicUnit = GraphicUnit.getGraphicUnit(unit);
            graphicUnits.add(graphicUnit);
        }
        return graphicUnits;
    }

    private static List<MapSpell> getGraphicMapSpells(Player player, Game game) {
        ArrayList<MapSpell> mapSpells = new ArrayList<>();

        for (Spell spell : game.getSpells()) {
            if(spell.getPlayer().getId() != player.getId()) continue ;
            MapSpell mapSpell = getMapSpell(spell);
            mapSpells.add(mapSpell);
        }

        return mapSpells;
    }

    private static MapSpell getMapSpell(Spell spell) {
        MapSpell mapSpell = new MapSpell();
        mapSpell.setCenter(new GraphicCell(spell.getPosition().getRow(), spell.getPosition().getCol()));
        mapSpell.setRange(spell.getRange());
        mapSpell.setTypeId(spell.getBaseSpell().getTypeId());
        mapSpell.setSpellId(spell.getId());
        return mapSpell;
    }
}
