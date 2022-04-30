package tank.abstractfactory;

import tank.Dir;
import tank.GameModel;
import tank.Group;
import tank.TankFrame;

public abstract class GameFactory {
	public abstract BaseTank createTank(int x, int y, Dir dir, Group group, GameModel gm);
	public abstract BaseExplode createExplode(int x, int y, GameModel gm);
	public abstract BaseBullet createBullet(int x, int y, Dir dir, Group group, GameModel gm);
}
